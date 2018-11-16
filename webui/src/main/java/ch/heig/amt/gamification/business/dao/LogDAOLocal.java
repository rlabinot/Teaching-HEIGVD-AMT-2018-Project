/**
 * Interface to communicate with the database to manage the logs of stackoveramt.
 * @author Dejvid Muaremi
 */
package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.Log;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface LogDAOLocal {
    /**
     * count all the logs from the database
     * @return the number of logs in the database
     */
    public int countLog();

    /**
     * Add a new log in the database.
     * @param log the log to add.
     */
    public void createLog(Log log);

    /**
     * Get a log from the database
     * @param logId the id of the log to get
     * @return a Log or null if it can't be found.
     */
    public Log readLog(int logId);

    /**
     * Get all the logs of the database
     * @return an ArrayList with all the logs in the database
     */
    public ArrayList<Log> readAllLog();

    /**
     * Get a sample of the logs in the database
     * @param offset the offset of the first log in the database.
     * @param size the number of logs to get from the database.
     * @return an ArrayList containing a sample of the logs in the database
     */
    public ArrayList<Log> readAllLog(int offset, int size);

    /**
     * update the log with the new values
     * @param idToUpdate the log to update
     * @param values the new values of the log
     */
    public void updateLog(int idToUpdate, Log values);

    /**
     * delete a log from the database
     * @param logId the id of the log to delete.
     */
    public void deleteLog(int logId);
}
