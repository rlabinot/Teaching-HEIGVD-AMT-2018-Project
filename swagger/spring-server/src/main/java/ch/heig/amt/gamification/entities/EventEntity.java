package ch.heig.amt.gamification.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
public class EventEntity implements Serializable {
    @Id
    private String eventType;

    private int userId;
    //private List<String> eventProperties;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    // TODO: Be careful using list because it makes entityManagerFactory Exception, still a mystery
    /**public List<String> getEventProperties() {
        return eventProperties;
    }

    public void setEventProperties(List<String> eventProperties) {
        this.eventProperties = eventProperties;
    }*/
}
