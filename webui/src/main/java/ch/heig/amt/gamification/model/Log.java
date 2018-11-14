package ch.heig.amt.gamification.model;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Log {

    private int id;
    private String user;
    private Long date;
    private String status; // e.g: INFO, SEVERE...
    private String action; // e.g: login, add an application, reset a password...
    private String description;
    public static final String STATUS_INFO = "INFO";
    public static final String STATUS_WARNING = "WARNING";
    public static final String STATUS_SEVERE = "SEVERE";

    public Log(String user, Long date, String status, String action, String description) {
        this.user = user;
        this.date = date;
        this.status = status;
        this.action = action;
        this.description = description;
    }
    public Log(int id, String user, Long date, String status, String action, String description) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.status = status;
        this.action = action;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User='" + user +
                "\ndate=" + date +
                "\nstatus='" + status +
                "\naction='" + action +
                "\ndescription='" + description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
