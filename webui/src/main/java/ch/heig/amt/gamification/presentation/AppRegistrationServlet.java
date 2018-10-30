package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.ApplicationDAOLocal;
import ch.heig.amt.gamification.model.Application;
import ch.heig.amt.gamification.model.InputError;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AppRegistrationServlet extends HttpServlet {
    @EJB
    ApplicationDAOLocal applicationDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String apiKey = request.getParameter("apiKey");
        String apiSecret = request.getParameter("apiSecret");

        InputError inputError = new InputError();
        if (name == null || name.trim().equals("")) {
            inputError.setEmptyName(true);
        }
        if (description == null || description.trim().equals("")) {
            inputError.setEmptyDescription(true);
        }
        if (apiKey == null || apiKey.trim().equals("")) {
            inputError.setEmptyApiKey(true);
        }
        if (apiSecret == null || apiSecret.trim().equals("")) {
            inputError.setEmptyApiSecret(true);
        }

        request.setAttribute("name", name);
        request.setAttribute("description", description);
        request.setAttribute("apiKey", apiKey);
        request.setAttribute("apiSecret", apiSecret);

        // Check if no errors during all the registration
        if (inputError.checkErrors() == false) {
            // Add the app to the db.
            Application appToAdd = new Application(name,description,apiKey,apiSecret,(String) request.getSession().getAttribute("email"));

            applicationDAO.createApplication(appToAdd);

            request.setAttribute("name", name + " " + apiKey);
            request.getRequestDispatcher("/WEB-INF/pages/manageApps.jsp").forward(request, response);
        } else {
            request.setAttribute("inputError", inputError);
            request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
        }
    }
}
