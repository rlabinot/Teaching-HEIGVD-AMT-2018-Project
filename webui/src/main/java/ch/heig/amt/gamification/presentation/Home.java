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
        String email = "admin@stackoveramt.ch";
        boolean isAdmin = true;

        int pageSize;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = 10;
        }

        int itemCount = isAdmin ? userDAO.countUser() : appDAO.countApplication(email);
        int pageCount = (itemCount + pageSize - 1) / pageSize;


        int pageIndex = 0;
        String param = request.getParameter("pageIndex");
        try {
            pageIndex = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            if (param == null) {
                pageIndex = 0;
            }
            else if (param.equals("last")) {
                pageIndex = pageCount - 1;
            }
        }


        // To avoid url wrong pageIndex and pageSize injection
        if ((pageIndex * pageSize + 1 > itemCount && itemCount != 0)|| pageIndex < 0 || pageSize < 0)
        {
            // Avoid negative pageSize
            response.sendRedirect( "/webui/home?pageSize=10&pageIndex=0");
            return;
        }

        // Get the page displayer info
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("pageSize", pageSize);


        // Get all the rightful links
        String baseUrl = "/home?pageSize=" + pageSize + "&pageIndex=";
        request.setAttribute("firstPageLink", baseUrl + "0");
        request.setAttribute("lastPageLink", baseUrl + (pageCount - 1));
        request.setAttribute("prevPageLink", baseUrl + Math.max(0, pageIndex - 1));
        request.setAttribute("nextPageLink", baseUrl + Math.min(pageIndex + 1, pageCount - 1));


        if (isAdmin == true) {
            // retrieve all users from database and add them to the request
            request.setAttribute("users", userDAO.readAllUser(pageSize * pageIndex, pageSize));
            request.setAttribute("pageTitle", "Manage Users");
            request.getRequestDispatcher("/WEB-INF/pages/manageUsers.jsp").forward(request, response);
        } else {
            // Get all apps of a user from database according to the pagination
            request.setAttribute("apps", appDAO.readApplicationFromUser(email, pageSize * pageIndex, pageSize));
            request.setAttribute("pageTitle", "Manage Apps");
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "This is the home servlet. It redirects in the respective home page for each type of user.";
    }
}
