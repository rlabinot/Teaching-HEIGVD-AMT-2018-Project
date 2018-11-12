package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.User;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface UserDAOLocal {
    public void createUser(User userToCreate);
    public User readUser(String emailToRead);
    public ArrayList<User> readAllUser();
    public ArrayList<User> readAllUser(int offset, int size);
    public User userLogin(String emailToRead, String password);
    public void updateUser(String userToUpdate, User values);
    public void deleteUser(String userToDelete);
    public void changeUserState(String userEmail, boolean state);
    public void changeUserPassword(String mail, String password, boolean mustChangePassword);
}
