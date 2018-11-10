package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.ApplicationDAOLocal;
import ch.heig.amt.gamification.model.Application;
import ch.heig.amt.gamification.model.InputError;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


public class AppServlet extends HttpServlet {

    @EJB
    ApplicationDAOLocal applicationDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String appId = request.getParameter("id");
        action = action == null ? "" : action;
        switch (action) {
            case "":
                request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);

            case "delete":
                applicationDAO.deleteApplication(Integer.valueOf(appId).intValue());
                response.sendRedirect("/webui/home");
                break;

            case "edit":
                //Application appToEdit = null; //TODO: delete that as soon as possible
                Application appToEdit = applicationDAO.readApplication(Integer.parseInt(appId));
                request.setAttribute("app", appToEdit);
                request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
                break;

            case "show":
                //Application appToShow = null; //TODO: delete that as soon as possible
                Application appToShow = applicationDAO.readApplication(Integer.parseInt(appId));
                request.setAttribute("app", appToShow);
                request.getRequestDispatcher("/WEB-INF/pages/showApp.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("/webui/aksdjlakjd");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String apiKey = UUID.randomUUID().toString();
        String apiSecret = UUID.randomUUID().toString();

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
            response.sendRedirect("/webui/home");
        } else {
            request.setAttribute("inputError", inputError);
            request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
        }
    }
}
