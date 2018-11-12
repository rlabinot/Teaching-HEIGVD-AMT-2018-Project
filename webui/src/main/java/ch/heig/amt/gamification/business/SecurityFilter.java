package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.InputError;
import ch.heig.amt.gamification.model.User;

import java.io.IOException;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {
    /**
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // we use a whitelist system to define which url are protected or not
        boolean isTargetUrlProtected = true;
        if (path.equals("/")) {
            isTargetUrlProtected = false;
        } else if(path.startsWith("/static")) {
            isTargetUrlProtected = false;
        } else if (path.startsWith("/login")) {
            isTargetUrlProtected = false;
        } else if (path.startsWith("/user") && !path.contains("action")) {
            isTargetUrlProtected = false;
        }

        /*
         * If the user has been authenticated before, then the AuthenticationServlet has placed
         * an object (in this case a String) in the HTTP session. We can retrieve it.
         */
        String email = (String) httpRequest.getSession().getAttribute("email");

        if (email == null && isTargetUrlProtected) {
            /*
             * The user has not been authenticated and tries to access a protected resource,
             * we display the login page (and interrupt the request processing pipeline).
             */
            httpResponse.sendRedirect("/webui/login");

        } else {

            boolean mustChangePassword = Boolean.TRUE == httpRequest.getSession().getAttribute("mustChangePassword");
            boolean isActive= Boolean.TRUE == httpRequest.getSession().getAttribute("isActive");
            InputError error = new InputError();

            if (!isActive && email != null) {
                error.setInactiveUser(true);
                request.setAttribute("inputError", error);
                request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

            } else if (mustChangePassword && email != null) {
                // the filter should not redirect the action to change pwd
                if(httpRequest.getMethod().equals("POST")) {
                    chain.doFilter(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/pages/chngPassword.jsp").forward(request, response);
                }
            } else {

                /*
                 * We authorize the access, so we can tell the request processing pipeline to
                 * continue its work.
                 */
                chain.doFilter(request, response);
                /*
                 * Here, we could inspect and manipulate the response and its way back to the
                 * client. In this case, we don't have anything to do.
                 */
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
