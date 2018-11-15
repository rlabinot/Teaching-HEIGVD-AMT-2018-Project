package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.business.dao.LogDAOLocal;
import ch.heig.amt.gamification.model.Log;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {

    @EJB
    LogDAOLocal logDAO;
    private FilterConfig filterConfigObj = null;

    public void init(FilterConfig filterConfigObj) {
        this.filterConfigObj = filterConfigObj;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession httpSession = httpRequest.getSession();
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // 1) for the request and response, create a log object with the data we can retrieve
        // 2) send the log to the database

        chain.doFilter(req, resp);

        String remoteAddress;
        String remoteUser;
        String uri;
        String method;
        String action;
        String id;
        String description = "";
        String status;
        String server;


        try {
            remoteUser = httpSession.getAttribute("email").toString(); // TODO : check if it returns the username of a logged
        } catch (Exception e){
            remoteUser = "undefined";
        }

        method = httpRequest.getMethod();
        if(method == null){
            method = "undefined";
        }
        description += method;

        uri = httpRequest.getRequestURI();
        if(uri == null){
            uri = "undefined";
        }
        description += " " + uri;

        remoteAddress = req.getRemoteAddr();
        if(remoteAddress == null){
            remoteAddress = "undefined";
        }

        description += " from " + remoteAddress;

        action = httpRequest.getParameter("action");

        if(action != null && !action.equals("undefined")) {
            try {
                id = httpRequest.getParameter("id");
                action += " on id=" + id;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (path.startsWith("/login")) {
            action = "login attempt";

        } else if (path.startsWith("/home") && !remoteUser.equals("undefined")) {
            if (Boolean.TRUE == httpRequest.getSession().getAttribute("isAdmin")) {
                action = "view user list";
            } else {
                action = "view app list";
            }
        } else if (path.startsWith("/app") && !remoteUser.equals("undefined") && action == null) {
            action = "create new app";
        } else if (path.startsWith("/user") && remoteUser.equals("undefined") && action == null) {
            action = "register";
        } else {
            action = "-";
        }

        Log request_log = new Log(remoteUser, System.currentTimeMillis(), "request", action, description);

        logDAO.createLog(request_log);

        filterConfigObj.getServletContext().log(request_log.toString());

        try {
            status = String.valueOf(httpResponse.getStatus());
        } catch (Exception e){
            status = "undefined";
        }

        try {
            server = " : " + httpResponse.getHeader("Server");
        } catch (Exception e){
            server = "";
        }

        Log response_log = new Log(server, System.currentTimeMillis(), "response",
                "-", "Code : " + status);

        logDAO.createLog(response_log);

        filterConfigObj.getServletContext().log(response_log.toString());

    }
}
