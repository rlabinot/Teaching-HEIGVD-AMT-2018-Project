package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.UserDAOLocal;
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

        String action = request.getParameter("action");

        if(action == null) {
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        } else if (action.equals("logout")) {
            // LOGOUT part
            request.getSession().invalidate();
        } else if (action.equals("login")) {
            // LOGIN part
            // get an attribute of the session to know if it is active
            String email = (String) request.getSession().getAttribute("email");
            if (email == null) {
                // there is no session, so we redirect to the login page
                // don't forget to add errors in list
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            } else {
                // there is a session so we redirect to the user's home
                response.sendRedirect("/pages/home");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession httpSession;

        // check with db isConnected()
        // get the user via DB
        // User user = new User("admin","admin@gmail.com","12345", true,true);

        try {
            User user = userDAO.readUser(email);

            if (user != null) {
                // set session parameters
                httpSession = request.getSession();
                httpSession.setAttribute("name", user.getName());
                httpSession.setAttribute("email", email);
                httpSession.setAttribute("password", password);
                httpSession.setAttribute("isActive", user.isActive());
                httpSession.setAttribute("isAdmin", user.isAdmin());

                if (user.isActive()) {
                    // login ok with active user
                    response.sendRedirect("/pages/home");
                } else {
                    // login not ok because inactive user
                    request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
                }
            } else {
                // login is not ok
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            }

        } catch (Exception e){

            System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOON");
        }



    }

    @Override
    public String getServletInfo() {
        return "This is the login servlet. It check if the request is a login or a logout action and redirect the user to the respective page";
    }

}
