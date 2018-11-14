package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.Log;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface LogDAOLocal {
    public int countLog();
    public void createLog(Log log);
    public Log readLog(int logId);
    public ArrayList<Log> readAllLog();
    public ArrayList<Log> readAllLog(int offset, int size);
    public void updateLog(int idToUpdate, Log values);
    public void deleteLog(int logId);
}
