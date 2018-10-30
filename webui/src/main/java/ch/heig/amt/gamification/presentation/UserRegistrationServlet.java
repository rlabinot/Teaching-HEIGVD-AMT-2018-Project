package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.UserDAO;
import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


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
        }
        else {
            if (email.indexOf('@') == -1) {
                inputError.setWrongFormatEmail(true);
            }
        }

        request.setAttribute("name", name);
        request.setAttribute("password", password);
        request.setAttribute("email", email);

        // check if no errors
        if (inputError.checkErrors() == false) {

            // Ajout à la DB
            User userToAdd = new User(name, email, password, false, true);
            UserDAO userDAO = new UserDAO();
            userDAO.createUser(userToAdd);

            request.setAttribute("name", name + " " + password);
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        } else {
            request.setAttribute("inputError", inputError);
            request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
        }

    }

    private boolean checkPassword(String password) {
        // define a password complexity (one number, eight characters, one upper case, without blanks)
        String pattern = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

}

