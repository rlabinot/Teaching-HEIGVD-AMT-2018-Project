package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.EmailSender;
import ch.heig.amt.gamification.business.EmailSenderLocal;
import ch.heig.amt.gamification.business.dao.ApplicationDAOLocal;
import ch.heig.amt.gamification.business.dao.OldPasswordDAO;
import ch.heig.amt.gamification.business.dao.OldPasswordDAOLocal;
import ch.heig.amt.gamification.business.dao.UserDAOLocal;
import ch.heig.amt.gamification.model.Application;
import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.OldPassword;
import ch.heig.amt.gamification.model.User;
import org.apache.commons.codec.digest.DigestUtils;

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

    @EJB
    ApplicationDAOLocal appDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String userEmail = request.getParameter("id");

        action = action == null ? "" : action;
        switch (action) {
            case "":
                if (request.getSession().getAttribute("email") == null) {
                    request.setAttribute("pageTitle", "Register User");
                    request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/webui/home");
                }
                break;

            case "listapp":
                User user = userDAO.readUser(userEmail);
                List<Application> apps = appDAO.readApplicationFromUser(userEmail);
                request.setAttribute("user", user);
                request.setAttribute("apps", apps);
                request.setAttribute("pageTitle", "Manage Apps");
                request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
                break;

            case "delete":
                oldPasswordDAO.deleteAllOldPasswordFromUser(userEmail);
                appDAO.deleteAllApplicationFromUser(userEmail);
                userDAO.deleteUser(userEmail);
                response.sendRedirect("/webui/home");
                break;

            case "suspend":
                userDAO.changeUserState(userEmail, false);
                response.sendRedirect("/webui/home");
                break;

            case "activate":
                userDAO.changeUserState(userEmail, true);
                response.sendRedirect("/webui/home");
                break;

            case "resetPassword":
                // send mail
                String auto_password = UUID.randomUUID().toString();

                userDAO.changeUserPassword(userEmail, auto_password, true);
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
                request.setAttribute("pageTitle", "Error 404");
                response.sendRedirect("/webui/404");
        }

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
                inputError.setEmptyName(name == null || name.trim().equals(""));
                inputError.setEmptyEmail(email == null || email.trim().equals(""));
                inputError.setEmptyPassword(password == null || password.trim().equals(""));
                inputError.setWeakPassword(checkPassword(password) == false && !inputError.isEmptyPassword());
                inputError.setWrongFormatEmail(email.indexOf('@') == -1 && !inputError.isEmptyEmail());

                // check if no errors // TODO: Check if the email is not already in use !IMPORTANT
                if (inputError.checkErrors() == false) {
                    // Ajout à la DB
                    User userToAdd = new User(name, email, password, false, true, false);
                    OldPassword oldPassword = new OldPassword(email, password);
                    try {
                        userDAO.createUser(userToAdd);
                        oldPasswordDAO.createOldPassword(oldPassword);
                        response.sendRedirect("/webui/login");
                    } catch (Exception e) {
                        inputError.setEmailAlreadyInUse(true);
                        request.setAttribute("inputError", inputError);
                        request.setAttribute("pageTitle", "Register User");
                        request.getRequestDispatcher("/WEB-INF/pages/registerUser.jsp").forward(request, response);

                    }
                } else {
                    request.setAttribute("inputError", inputError);
                    request.setAttribute("pageTitle", "Register User");
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

                if (!inputError.checkErrors()) {

                    List oldPasswords = oldPasswordDAO.readAllOldPasswordFromUser(email);
                    // add UUID password in the old pwd list
                    oldPasswords.add(DigestUtils.sha256Hex(password));
                    inputError.setPasswordReused(oldPasswords.contains(DigestUtils.sha256Hex(password1)));

                    // check if the new password is not in the old password list, not in the 1st check for better performance
                    if (!inputError.checkErrors()) {

                        // set the new password
                        userDAO.changeUserPassword(email, password1, false);

                        // add this password to the list of old passwords
                        OldPassword old_password = new OldPassword(email, password1);
                        try {
                            oldPasswordDAO.createOldPassword(old_password);
                        } catch (Exception e){
                            // the tuple (email, password) already exists in the db, then send an error
                            inputError.setPasswordReused(true);
                            request.setAttribute("inputError", inputError);
                            request.setAttribute("pageTitle", "Change Password");
                            request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                        }

                        // change session attributes
                        httpSession.setAttribute("mustChangePassword", false);
                        httpSession.setAttribute("password", password1);
                        response.sendRedirect("/webui/home");

                    } else {
                        // send error
                        request.setAttribute("inputError", inputError);
                        request.setAttribute("pageTitle", "Change Password");
                        request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("inputError", inputError);
                    request.setAttribute("pageTitle", "Change Password");
                    request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                }
                break;

            default:
                // redirect to 404 page
                //response.sendRedirect("/webui/aksdjlakjd");
        }

    }

    // check the password complexity, return true if the password matches the security requirements
    private boolean checkPassword(String password) {
        // define a password complexity (one number, eight characters, one upper case, without blanks)
        String pattern = "(?=.*[0-9])(?=.*[A-Z])(?=\\S+$).{8,}";
        return password.matches(pattern);
    }

}

