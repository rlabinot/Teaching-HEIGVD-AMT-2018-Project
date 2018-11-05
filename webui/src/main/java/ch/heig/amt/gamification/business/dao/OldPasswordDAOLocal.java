package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.OldPassword;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface OldPasswordDAOLocal {
    public void createOldPassword(OldPassword oldPasswordToAdd);
    public ArrayList<OldPassword> readAllOldPasswordFromUser(String email);
    public void deleteAllOldPasswordFromUser(String email);
}
