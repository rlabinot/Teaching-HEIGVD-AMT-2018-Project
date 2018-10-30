package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.User;

import javax.ejb.Local;

@Local
public interface UserDAOLocal {
    public void createUser(User userToCreate);
    public User readUser(String emailToRead);
    public void updateUser(String userToUpdate, User values);
    public void deleteUser(String userToDelete);
}
