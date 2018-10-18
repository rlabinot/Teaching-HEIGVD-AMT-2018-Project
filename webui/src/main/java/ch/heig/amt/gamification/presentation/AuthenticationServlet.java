package ch.heig.amt.gamification.presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    /*
     Get the parameter values, which have been transmitted either in the query string
     (for GET requests) or in the body (for POST requests).
     */
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

    /*
     When the user is not logged in yet and tries to access /pages/xxx, then he
     is redirected to the login page by the security filter. The security filter
     stores the targer url (/pages/xxx) in the request context, so that we can
     send redirect the user to the desired location after successful authentication.

     If the user accessed /auth directly and there is no targetUrl, then we send him
     to the home page.
     */
        String targetUrl = request.getParameter("targetUrl");
        if (targetUrl == null) {
            targetUrl = "/pages/login";
        }
        targetUrl = request.getContextPath() + targetUrl;

        if ("login".equals(action)) {
            request.getSession().setAttribute("principal", email);
            response.sendRedirect(targetUrl);
        } else if ("logout".equals(action)) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath());
        } else {
            response.sendRedirect(targetUrl);
        }

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
