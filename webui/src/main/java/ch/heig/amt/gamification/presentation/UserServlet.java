package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.EmailSender;
import ch.heig.amt.gamification.business.EmailSenderLocal;
import ch.heig.amt.gamification.business.dao.OldPasswordDAO;
import ch.heig.amt.gamification.business.dao.OldPasswordDAOLocal;
import ch.heig.amt.gamification.business.dao.UserDAOLocal;
import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.User;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class UserServlet extends HttpServlet {

    @EJB
    UserDAOLocal userDAO;

    @EJB
    OldPasswordDAOLocal oldPasswordDAO;

    @EJB
    EmailSenderLocal emailSender;

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
                userDAO.changeUserState(userEmail, false);
                response.sendRedirect("/webui/home");
                break;

            case "changePassword":
                // send mail
                String auto_password = UUID.randomUUID().toString();
                userDAO.resetUserPassword(userEmail);
                userDAO.changeUserPassword(userEmail, auto_password);
                String subject = "StackOverAMT : change your password !";
                String message = "Dear customer,\r\n\r\nYour password on stackoveramt.ch has been reset, please login " +
                        "with the following password : " + auto_password + " and set a new password after your next login." +
                        "\r\n\r\nBest,\r\nYour StackOverAMT team";

                try {
                    emailSender.send(userEmail, subject, message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

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
            InputError inputError = new InputError();
            // TODO: Declare variable here --> certainly there is a crash when changing password action
            switch (action) {
                case "":
                    // Get posted parameter
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");

                    // Check for errors
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
                    email = httpSession.getAttribute("email").toString();
                    password = httpSession.getAttribute("password").toString();

                    // both new given passwords, should be equal
                    String password1 = request.getParameter("password");
                    String password2 = request.getParameter("password2");

                    inputError.setBothPasswordDifferent(!password1.equals(password2));
                    inputError.setWeakPassword(!checkPassword(password1));

                    if (!inputError.checkErrors()){

                        List oldPasswords = oldPasswordDAO.readAllOldPasswordFromUser(email);
                        // add UUID password in the old pwd list
                        oldPasswords.add(password);
                        inputError.setPasswordReused(oldPasswordDAO.readAllOldPasswordFromUser(email).contains(password1));

                        // check if the new password is not in the old password list, not in the 1st check for better performance
                        if (!inputError.checkErrors()){

                            // set the new password
                            userDAO.changeUserPassword(email, password1);
                            httpSession.setAttribute("mustChangePassword", false);
                            httpSession.setAttribute("password", password1);
                            //response.sendRedirect("/webui/home");
                            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
                        } else {
                            // send error
                            request.setAttribute("inputError", inputError);
                            request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("inputError", inputError);
                        request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                    }
                    break;

                default:
                    // redirect to 404 page
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

    // check the password complexity, return true if the password matches the security requirements
    private boolean checkPassword(String password) {
        // define a password complexity (one number, eight characters, one upper case, without blanks)
        String pattern = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

}

