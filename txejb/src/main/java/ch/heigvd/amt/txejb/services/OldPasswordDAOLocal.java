package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.OldPassword;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface OldPasswordDAOLocal {
    public void createOldPassword(OldPassword oldPasswordToAdd);
    public ArrayList<OldPassword> readAllOldPasswordFromUser(String email);
    public void deleteAllOldPasswordFromUser(String email);
}
