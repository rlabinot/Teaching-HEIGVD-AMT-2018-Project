package ch.heig.amt.gamification.entities;
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class BadgeRewardEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer rewardId;
    private Long timestamp;
    private String reason;

    @ManyToOne
    private BadgeEntity badge;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ApplicationEntity application;

    @ManyToOne
    private EventEntity event;


    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }
}
