package ch.heig.amt.gamification.business.dao;

import ch.heig.amt.gamification.model.Application;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {
    public void createApplication(Application application);
    public Application readApplication(int appID, String email);
    public ArrayList<Application> readApplicationFromUser(String email);
    public ArrayList<Application> readApplicationFromUser(String email, int offset, int size);
    public void updateApplication(int appId, String name, String description, String email);
    public void deleteApplication(int appId, String email);
    public void deleteAllApplicationFromUser(String email);
}
