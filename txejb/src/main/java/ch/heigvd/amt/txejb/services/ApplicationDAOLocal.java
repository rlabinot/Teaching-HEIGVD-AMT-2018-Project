package ch.heigvd.amt.txejb.services;

import ch.heigvd.amt.txejb.model.Application;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {
    public void createApplication(Application application);
    public int countApplication(String email);
    public Application readApplication(int appID, String email);
    public ArrayList<Application> readApplicationFromUser(String email);
    public ArrayList<Application> readApplicationFromUser(String email, int offset, int size);
    public void updateApplication(int appId, String name, String description, String email);
    public void deleteApplication(int appId, String email);
    public void deleteAllApplicationFromUser(String email);
}
