package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.LogDAOLocal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogServlet extends HttpServlet {
    @EJB
    LogDAOLocal logDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageSize;
        try {
            pageSize = Integer.parseInt(request.getParameter("pageSize"));
        } catch (NumberFormatException e) {
            pageSize = 10;
        }

        int itemCount = logDAO.countLog();
        int pageCount = (itemCount + pageSize - 1) / pageSize;


        int pageIndex;
        String param = request.getParameter("pageIndex");
        try {
            pageIndex = Integer.parseInt(param);
        } catch (NumberFormatException e) {
                pageIndex = 0;
        }

        // To avoid url wrong pageIndex and pageSize injection
        if (pageIndex * pageSize + 1 > itemCount || pageIndex < 0 || pageSize < 0) {
            // Avoid negative pageSize
            response.sendRedirect("/webui/log?pageSize=10&pageIndex=0");
            return;
        }

        // Get the page displayer info
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("pageSize", pageSize);


        // Get all the rightful links
        String baseUrl = "/log?pageSize=" + pageSize + "&pageIndex=";
        request.setAttribute("firstPageLink", baseUrl + "0");
        request.setAttribute("lastPageLink", baseUrl + (pageCount - 1));
        request.setAttribute("prevPageLink", baseUrl + Math.max(0, pageIndex - 1));
        request.setAttribute("nextPageLink", baseUrl + Math.min(pageIndex + 1, pageCount - 1));



        // retrieve all users from database and add them to the request
        request.setAttribute("logs", logDAO.readAllLog(pageSize * pageIndex, pageSize));
        request.setAttribute("pageTitle", "Logs Page");
        request.getRequestDispatcher("/WEB-INF/pages/log.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "This is the Log servlet. It construct and show the list of logs.";
    }
}
