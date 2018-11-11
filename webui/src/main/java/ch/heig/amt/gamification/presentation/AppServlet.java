package ch.heig.amt.gamification.presentation;

import ch.heig.amt.gamification.business.dao.ApplicationDAOLocal;
import ch.heig.amt.gamification.model.Application;
import ch.heig.amt.gamification.model.InputError;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;


public class AppServlet extends HttpServlet {

    @EJB
    ApplicationDAOLocal applicationDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String userEmail = userSession.getAttribute("email").toString();
        String action = request.getParameter("action");
        String appId = request.getParameter("id");
        action = action == null ? "" : action;
        switch (action) {
            case "":
                request.setAttribute("pageTitle", "Register App");
                request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);

            case "delete":
                applicationDAO.deleteApplication(Integer.parseInt(appId), userEmail);
                response.sendRedirect("/webui/home");
                break;

            case "edit":
                Application appToEdit = applicationDAO.readApplication(Integer.parseInt(appId), userEmail);
                if (appToEdit == null) {
                    response.sendRedirect("/webui/home");
                } else {
                    request.setAttribute("app", appToEdit);
                    request.setAttribute("pageTitle", "Register App");
                    request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
                }
                break;

            case "show":
                Application appToShow = applicationDAO.readApplication(Integer.parseInt(appId), userEmail);
                request.setAttribute("app", appToShow);
                if (appToShow == null) {
                    response.sendRedirect("/webui/home");
                } else {
                    request.setAttribute("pageTitle", "Show App");
                    request.getRequestDispatcher("/WEB-INF/pages/showApp.jsp").forward(request, response);
                }
                break;

            default:
                request.setAttribute("pageTitle", "Error 404");
                request.getRequestDispatcher("/WEB-INF/pages/404.jsp").forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String userEmail = userSession.getAttribute("email").toString();
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String apiKey;
        String apiSecret;

        InputError inputError = new InputError();
        inputError.setEmptyName( name == null || name.trim().equals("") );
        inputError.setEmptyDescription( description == null || description.trim().equals("") );

        // Check if no errors during all the registration
        if (inputError.checkErrors() == false) {
            if (id == null) {
                apiKey = UUID.randomUUID().toString();
                apiSecret = UUID.randomUUID().toString();

                // Add the app to the db.
                Application appToAdd = new Application(name, description, apiKey, apiSecret, (String) request.getSession().getAttribute("email"));
                applicationDAO.createApplication(appToAdd);
            } else {
                // Edit the app in the db.
                applicationDAO.updateApplication(Integer.parseInt(id), name, description, userEmail);
            }

            response.sendRedirect("/webui/home");
        } else {
            request.setAttribute("inputError", inputError);
            request.setAttribute("pageTitle", "Register App");
            request.getRequestDispatcher("/WEB-INF/pages/registerApp.jsp").forward(request, response);
        }
    }
}
