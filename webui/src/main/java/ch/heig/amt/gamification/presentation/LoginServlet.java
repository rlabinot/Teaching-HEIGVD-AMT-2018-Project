package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.ToolBoxMySQL;
import ch.heig.amt.gamification.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action.equals("logout")) {
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

        // check with db isConnected()
        // get the user via DB
        //User user = new User("admin","admin@gmail.com","12345", true,true);

        ToolBoxMySQL toolBoxMySQL = new ToolBoxMySQL();
        toolBoxMySQL.initConnection();
        User user = toolBoxMySQL.readUser(email);
        toolBoxMySQL.closeConnection();
        if (user != null) {
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
    }

    @Override
    public String getServletInfo() {
        return "This is the login servlet. It check if the request is a login or a logout action and redirect the user to the respective page";
    }

}
