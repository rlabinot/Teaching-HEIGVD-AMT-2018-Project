package ch.heig.amt.gamification.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
        if (isAdmin == true) {
            request.getRequestDispatcher("/WEB-INF/pages/manageUsers.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "This is the home servlet. It redirects in the respective home page for each type of user.";
    }
}
