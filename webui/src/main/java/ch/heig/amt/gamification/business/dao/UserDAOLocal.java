/**
 * Interface to communicate with the database to manage the users of stackoveramt.
 * @author Dejvid Muaremi
 */
package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.User;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface UserDAOLocal {
    /**
     * Count the number of user in the database.
     * @return the number of user in the database
     */
    public int countUser();

    /**
     * Add an user in the database.
     * @param userToCreate the user to add to the database
     */
    public void createUser(User userToCreate);

    /**
     * Get an user from the database.
     * @param emailToRead the email of the user to get
     * @return an complete user with all his data, null if he can't be found.
     */
    public User readUser(String emailToRead);

    /**
     * Get all the users from the database.
     * @return an ArrayList with all the users found.
     */
    public ArrayList<User> readAllUser();
    /**
     * Get a sample of the users from the database.
     * @param offset the number of the first user to get
     * @param size the number of user to get
     * @return an ArrayList with all the users found.
     */
    public ArrayList<User> readAllUser(int offset, int size);

    /**
     * Try to login the user with his password.
     * @param emailToRead the email from the user who want to login
     * @param password the password from the user who want to login
     * @return the user if he's logged in, null if he can't be found.
     */
    public User userLogin(String emailToRead, String password);

    /**
     * update all the data from an user with another one
     * @param userToUpdate the email of the user we want to update
     * @param values the new values for the user.
     */
    public void updateUser(String userToUpdate, User values);

    /**
     * delete a given user.
     * @param userToDelete the email of the user to delete.
     */
    public void deleteUser(String userToDelete);

    /**
     * change the state of an user, if he's active or not.
     * @param userEmail the email of the user we want to change his state.
     * @param state the new state of the user.
     */
    public void changeUserState(String userEmail, boolean state);

    /**
     * Change the password of a user and set the boolean to force him to change his password or not.
     * @param mail the email of the user
     * @param password the new password
     * @param mustChangePassword the user must change his password when an admin change it
     */
    public void changeUserPassword(String mail, String password, boolean mustChangePassword);
}
