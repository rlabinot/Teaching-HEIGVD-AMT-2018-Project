package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;

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
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
