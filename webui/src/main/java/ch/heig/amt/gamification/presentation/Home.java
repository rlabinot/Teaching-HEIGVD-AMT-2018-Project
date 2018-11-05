package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.ApplicationDAOLocal;
import ch.heig.amt.gamification.business.dao.UserDAOLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home extends HttpServlet {

    @EJB
    ApplicationDAOLocal appDAO;

    @EJB
    UserDAOLocal userDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
        String email = (String) request.getSession().getAttribute("email");
        if (isAdmin == true) {
            // retrieve all users from database and add them to the request
            request.setAttribute("users", userDAO.readAllUser());
            request.getRequestDispatcher("/WEB-INF/pages/manageUsers.jsp").forward(request, response);
        } else {
            // retrieve all apps of a user from database and add them to the request
            request.setAttribute("apps", appDAO.readApplicationFromUser(email));
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "This is the home servlet. It redirects in the respective home page for each type of user.";
    }
}
