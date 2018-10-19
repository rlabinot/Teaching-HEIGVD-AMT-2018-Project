package ch.heig.amt.gamification.business;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

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
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        boolean isTargetUrlProtected = true;

        if (path.startsWith("/login/")) {
            isTargetUrlProtected = false;
        } if (path.startsWith("/register/")) {
            isTargetUrlProtected = false;
        } else {
            /*
             * Let's imagine that the user has sent a request to /MVCDemo/pages/beers before logging into the
             * application. In that case, we want to route the user to the login page. If he provides valid
             * credentials, then we then want to redirect the user to /MVCDemo/pages/beers. In order to do that,
             * we need to save the target URL
             */
            request.setAttribute("targetUrl", path);

        }

        /*
         * If the user has been authenticated before, then the AuthenticationServlet has placed
         * an object (in this case a String) in the HTTP session. We can retrieve it.
         */
        String principal = (String) httpRequest.getSession().getAttribute("principal");
        if (principal == null && isTargetUrlProtected) {
            /*
             * The user has not been authenticated and tries to access a protected resource,
             * we display the login page (and interrupt the request processing pipeline).
             */
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}