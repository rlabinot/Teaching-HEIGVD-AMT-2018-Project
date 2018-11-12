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
        } else {
            action = "undefined";
        }

        Log request_log = new Log(remoteUser, System.currentTimeMillis(), Log.STATUS_INFO, action, description);

        logDAO.createLog(request_log);

        filterConfigObj.getServletContext().log("\nRequest : \nLoggedUser: " + remoteUser +
                "\nAction: " + action +
                "\nDescription" + description);

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

        Log response_log = new Log("Server" + server, System.currentTimeMillis(), Log.STATUS_INFO,
                "server response", "Code : " + status);

        logDAO.createLog(response_log);

        filterConfigObj.getServletContext().log(server + "\nResponse :" +
                "\nStatus: " + status);

    }
}
