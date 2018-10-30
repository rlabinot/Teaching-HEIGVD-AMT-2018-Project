package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.Log;

import javax.ejb.Local;

@Local
public interface LogDAOLocal {
    public void createLog(Log log);
    public Log readLogFromDate(long date);
    public void updateLog();
    public void deleteLog(int logId);
}
