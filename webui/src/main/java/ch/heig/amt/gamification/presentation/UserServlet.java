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

public class UserServlet extends HttpServlet {

    @EJB
    UserDAOLocal userDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userEmail = request.getParameter("id");

        action = action == null ? "" : action;
        switch (action) {
            case "":
                if (request.getSession().getAttribute("email") == null) {
                    request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/webui/home");
                }
                break;

            case "delete":
                userDAO.deleteUser(userEmail);
                response.sendRedirect("/webui/home");
                break;

            case "suspend":
                userDAO.changeUserState(userEmail);
                response.sendRedirect("/webui/home");
                break;

            case "changePassword":
                userDAO.resetUserPassword(userEmail);
                response.sendRedirect("/webui/home");
                break;

            default:
                response.sendRedirect("/webui/aksdjlakjd");
        }

        /* OLD STYLE BACKUP JUST IN CASE TODO: Delete if switch version is accepted
        if(action == null) {
            if (request.getSession().getAttribute("email") == null) {
                request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("/webui/home");
            }
        } else if (action.equals("delete")) {
            String userEmail = request.getParameter("id");
            userDAO.deleteUser(userEmail);
            response.sendRedirect("/webui/home");
        }
     */
    }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

            action = action == null ? "" : action;
            // TODO: Declare variable here --> certainly there is a crash when changing password action
            switch (action) {
                case "":
                    // Get posted parameter
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");

                    // Check for errors
                    InputError inputError = new InputError();
                    inputError.setEmptyName( name == null || name.trim().equals("") );
                    inputError.setEmptyEmail( email == null || email.trim().equals("") );
                    inputError.setEmptyPassword( password == null || password.trim().equals("") );
                    inputError.setWeakPassword( checkPassword(password) == false && !inputError.isEmptyPassword() );
                    inputError.setWrongFormatEmail( email.indexOf('@') == -1 && !inputError.isEmptyEmail() );

                    // check if no errors // TODO: Check if the email is not already in use !IMPORTANT
                    if (inputError.checkErrors() == false) {
                        // Ajout à la DB
                        User userToAdd = new User(name, email, password, false, true, false);
                        userDAO.createUser(userToAdd);
                        response.sendRedirect("/webui/login");
                    } else {
                        request.setAttribute("inputError", inputError);
                        request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
                    }
                    break;

                case "changePassword":
                    HttpSession httpSession = request.getSession();

                    // define the user parameters
                    name = httpSession.getAttribute("name").toString();
                    email = httpSession.getAttribute("email").toString();
                    boolean isAdmin = (boolean) httpSession.getAttribute("isAdmin");
                    boolean isActive = (boolean) httpSession.getAttribute("isActive");
                    password = httpSession.getAttribute("password").toString();

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
                    break;

                default:
                    response.sendRedirect("/webui/aksdjlakjd");
            }

        /*
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
            else {

                if (checkPassword(password) == false) {
                    inputError.setWeakPassword(true);
                }

                if (email.indexOf('@') == -1 && !(email == null || email.trim().equals(""))) {
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
                response.sendRedirect("/webui/login");
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

        */

    }

    private boolean checkPassword(String password) {
        // define a password complexity (one number, eight characters, one upper case, without blanks)
        String pattern = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

}

