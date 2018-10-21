package ch.heig.amt.gamification.presentation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppRegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String apiKey = request.getParameter("apiKey");
        String apiSecret = request.getParameter("apiSecret");

        List<String> errors = new ArrayList<>();
        if (name == null || name.trim().equals("")) {
            errors.add("Name cannot be empty");
        }
        if (description == null || description.trim().equals("")) {
            errors.add("Description cannot be empty");
        }
        if (apiKey == null || apiKey.trim().equals("")) {
            errors.add("ApiKey cannot be empty");
        }
        if (apiSecret == null || apiSecret.trim().equals("")) {
            errors.add("ApiSecret cannot be empty");
        }

        request.setAttribute("name", name);
        request.setAttribute("description", description);
        request.setAttribute("apiKey", apiKey);
        request.setAttribute("apiSecret", apiSecret);
        if (errors.size() == 0) {
            // Ajout à la DB
            request.setAttribute("name", name + " " + apiKey);
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        } else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
        }
    }
}
