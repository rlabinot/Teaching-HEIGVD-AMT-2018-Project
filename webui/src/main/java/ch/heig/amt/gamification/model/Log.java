package ch.heig.amt.gamification.model;

import java.sql.Timestamp;
import java.util.LinkedList;

public class Log {

    private int id;
    private Timestamp date;
    private String status;
    private String action;
    private String description;
    private LinkedList<Log> logs;

    public Log(int id, Timestamp date, String status, String action, String description) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.action = action;
        this.description = description;
        this.logs = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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

    public LinkedList<Log> getLogs() {
        return logs;
    }

    public void setLogs(LinkedList<Log> logs) {
        this.logs = logs;
    }

}
