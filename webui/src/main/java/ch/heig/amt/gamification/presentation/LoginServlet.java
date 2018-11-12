package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.UserDAOLocal;
import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.User;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @EJB
    UserDAOLocal userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pageTitle", "Login");
        String action = request.getParameter("action");

        if(action == null) {
            String email = (String) request.getSession().getAttribute("email");
            if (email == null) {
                // there is no session, so we redirect to the login page
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            } else {
                // there is a session so we redirect to the user's home (home servlet)
                response.sendRedirect("/webui/home");
            }
        } else if (action.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("/webui/login");

        } else {
            response.sendRedirect("/webui/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession httpSession;
        InputError error = new InputError();

        try {
            User user = userDAO.userLogin(email, password);

            if (user != null) {
                // set session parameters
                httpSession = request.getSession();
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("email", email);
                httpSession.setAttribute("password", password);
                httpSession.setAttribute("isActive", user.isActive());
                httpSession.setAttribute("isAdmin", user.isAdmin());
                httpSession.setAttribute("mustChangePassword", user.getMustChangePassword());

                if (user.isActive()) {
                    if (!user.getMustChangePassword()){
                        // login ok with active user
                        response.sendRedirect("/webui/home");
                    } else {
                        // user must change his password
                        request.setAttribute("pageTitle", "Change Password");
                        request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                    }
                } else {
                    // login not ok because inactive user
                    request.getSession().invalidate();
                    error.setInactiveUser(true);
                    request.setAttribute("inputError", error);
                    request.setAttribute("pageTitle", "Login");
                    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
                }
            } else {
                // login is not ok because wrong credentials, db result is null
                request.getSession().invalidate();
                error.setWrongLogin(true);
                request.setAttribute("inputError", error);
                request.setAttribute("pageTitle", "Login");
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            }
        } catch (Exception e){
            e.printStackTrace();
        }



    }

    @Override
    public String getServletInfo() {
        return "This is the login servlet. It check if the request is a login or a logout action and redirect the user to the respective page";
    }

}
