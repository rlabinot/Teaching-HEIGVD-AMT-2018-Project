package ch.heig.amt.gamification.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EventEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer eventId;
    private Long timestamp;
    private String eventType;

    @ManyToOne
    private  UserEntity user;
    @ManyToOne
    private ApplicationEntity application;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
