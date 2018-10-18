package ch.heig.amt.gamification.model;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Log {

    private Timestamp date;
    private int id;
    private String status;
    private String action;
    private String description;
    private LinkedList<Log> logs;

    public Log(Timestamp date, int id, String status, String action, String description) {
        this.date = date;
        this.id = id;
        this.status = status;
        this.action = action;
        this.description = description;
        logs = new LinkedList<>();
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
