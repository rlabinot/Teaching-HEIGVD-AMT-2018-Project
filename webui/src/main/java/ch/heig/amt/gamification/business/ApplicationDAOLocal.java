package ch.heig.amt.gamification.business;

import ch.heig.amt.gamification.model.Application;

import java.util.ArrayList;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal {
    public void createApplication(Application application);
    public ArrayList<Application> readApplicationFromUser(String email);
    public void updateApplication(int appId, Application values);
    public void deleteApplication(int appId);
}
