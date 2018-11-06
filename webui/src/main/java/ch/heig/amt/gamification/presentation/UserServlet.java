package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.UserDAOLocal;
import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServlet extends HttpServlet {

    @EJB
    UserDAOLocal userDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null) {
            if (request.getSession().getAttribute("email") != null) {
                request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("/webui/home");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        // base action is registration of the user
        if(action == null) {
            // Get posted parameter
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");


            // Check for errors
            InputError inputError = new InputError();
            if (name == null || name.trim().equals("")) {
                inputError.setEmptyName(true);
            }
            if (email == null || email.trim().equals("")) {
                inputError.setEmptyEmail(true);
            }
            if (password == null || password.trim().equals("")) {
                inputError.setEmptyPassword(true);
            }
            if (checkPassword(password) == false) {
                inputError.setWeakPassword(true);
            } else {
                if (email.indexOf('@') == -1) {
                    inputError.setWrongFormatEmail(true);
                }
            }

            // TODO: Check if the email is not already in use !IMPORTANT

            // check if no errors
            if (inputError.checkErrors() == false) {

                // Ajout à la DB
                User userToAdd = new User(name, email, password, false, true, false);
                userDAO.createUser(userToAdd);

                //
                request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
            } else {
                request.setAttribute("inputError", inputError);
                request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
            }

        } else if (action == "changePassword") {
            HttpSession httpSession = request.getSession();

            // define the user parameters
            String name = httpSession.getAttribute("name").toString();
            String email = httpSession.getAttribute("email").toString();
            boolean isAdmin = (boolean) httpSession.getAttribute("isAdmin");
            boolean isActive = (boolean) httpSession.getAttribute("isActive");
            String password = httpSession.getAttribute("password").toString();

            // both new given passwords, should be equal
            String password1 = request.getParameter("password");
            String password2 = request.getParameter("password2");

            if (password1.equals(password2)){

                // TODO : check if its not an old password
                // create the user with a new password to update the db
                User updatedUser = new User(name, email, password1, isAdmin, isActive, false);
                
                // update the database
                userDAO.updateUser(email, updatedUser);
                httpSession.setAttribute("mustChangePassword", false);
                response.sendRedirect("/webui/home");

            } else { // both passwords are different
                request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                // TODO : should I send an error ?
            }
        }

    }

    private boolean checkPassword(String password) {
        // define a password complexity (one number, eight characters, one upper case, without blanks)
        String pattern = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

}

