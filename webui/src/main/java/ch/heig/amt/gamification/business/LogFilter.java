package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {

    private FilterConfig filterConfigObj = null;

    public void init(FilterConfig filterConfigObj) {
        this.filterConfigObj = filterConfigObj;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        String user = (String) httpRequest.getSession().getAttribute("user");
        Timestamp date = new Timestamp(System.currentTimeMillis() % 1000);
        String action = (String) httpRequest.getSession().getAttribute("action");
        String description = (String) httpRequest.getSession().getAttribute("description");

        if(user == null){
            user = "";
            description = "login attempt";
        }

        // 1) create a log object with the data from session
        //Log log = new Log(0, user, date, "lala", action, description);
        // 2) send the log to the database
        chain.doFilter(req, resp);

        String remoteAddress;
        String remoteUser;
        String uri;
        String protocol;
        String header;
        String method;
        String parts;

        try {
            remoteAddress = req.getRemoteAddr();
        } catch (Exception e){
            remoteAddress = "undefined";
        }
        try {
            remoteUser = httpRequest.getRemoteUser(); // TODO : check if it returns the username of a logged
        } catch (Exception e){
            remoteUser = "undefined";
        }
        try {
            uri = httpRequest.getRequestURI();
        } catch (Exception e){
            uri = "undefined";
        }
        try {
            protocol = req.getProtocol();
        } catch (Exception e){
            protocol = "undefined";
        }
        try {
            header = httpRequest.getHeaderNames().toString();
        } catch (Exception e){
            header = "undefined";
        }
        try {
        method = httpRequest.getMethod();
        } catch (Exception e){
            method = "undefined";
        }
        try {
            parts = httpRequest.getParts().toString();
        } catch (Exception e){
            parts = "undefined";
        }

        filterConfigObj.getServletContext().log("\nRequest : LoggedUser: " + remoteUser + "\nUser IP: " + remoteAddress +
                "\nResource File: " + uri + "\nProtocol: " + protocol + "\nHeader: " + header + "\nAction: " + method +
                "\nParts: " + parts);

        String contentType;
        String status;
        String headersName;

        try {
            contentType = resp.getContentType();
        } catch (Exception e){
            contentType = "undefined";
        }
        try {
            status = String.valueOf(httpResponse.getStatus());
        } catch (Exception e){
            status = "undefined";
        }
        try {
            headersName = httpResponse.getHeaderNames().toString();
        } catch (Exception e){
            headersName = "undefined";
        }

        filterConfigObj.getServletContext().log("\nResponse : ContentType: " + contentType +
                "\nStatus: " + status +
                "\nHeadersName: " + headersName);

    }



}
