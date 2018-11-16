/**
 * Interface to communicate with the database to manage the old passwords of stackoveramt.
 * @author Dejvid Muaremi
 */
package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.OldPassword;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface OldPasswordDAOLocal {
    /**
     * Add a new old password to the database
     * @param oldPasswordToAdd the new old password to add
     */
    public void createOldPassword(OldPassword oldPasswordToAdd);

    /**
     * Get all the old passwords of the user.
     * @param email the email of the user.
     * @return an ArrayList with all the old passwords
     */
    public ArrayList<OldPassword> readAllOldPasswordFromUser(String email);

    /**
     * Remove all the old passwords of an user.
     * @param email the email of the user to delete his old passwords
     */
    public void deleteAllOldPasswordFromUser(String email);
}
