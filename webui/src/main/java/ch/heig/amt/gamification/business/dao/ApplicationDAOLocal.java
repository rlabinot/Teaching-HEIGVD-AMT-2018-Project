/**
 * Interface to communicate with the database to manage the applications of stackoveramt.
 * @author Dejvid Muaremi
 */
package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.Application;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {
    /**
     * Add a new application in the database
     * @param application the application to add.
     */
    public void createApplication(Application application);

    /**
     * count the number of application owned by an user
     * @param email the user to count his application
     * @return the number of application owned by an user
     */
    public int countApplication(String email);

    /**
     * Get the application of an user
     * @param appID the id of the application to read
     * @param email the user who has this application
     * @return an Application or null if it can't be found
     */
    public Application readApplication(int appID, String email);

    /**
     * Get all the Applications owned by a user from the database.
     * @param email the user to list his applications
     * @return an ArrayList with all the application owned by the user found.
     */
    public ArrayList<Application> readApplicationFromUser(String email);

    /**
     * Get a sample of the Applications owned by a user from the database.
     * @param email the user to list his applications
     * @param offset the number of the first application to get
     * @param size the number of applications to get
     * @return an ArrayList with a sample of the Applications owned by a user from the database
     */
    public ArrayList<Application> readApplicationFromUser(String email, int offset, int size);

    /**
     * Update an existing application with some new values
     * @param appId the id of the application to update
     * @param name the new name of the application
     * @param description the new description of the application
     * @param email the user who own the application
     */
    public void updateApplication(int appId, String name, String description, String email);

    /**
     * delete the given application
     * @param appId the id of the application
     * @param email the user who own the application
     */
    public void deleteApplication(int appId, String email);

    /**
     * delete all the application of an user
     * @param email the user to delete his applications
     */
    public void deleteAllApplicationFromUser(String email);
}
