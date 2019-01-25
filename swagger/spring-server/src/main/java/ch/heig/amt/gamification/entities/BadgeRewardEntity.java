package ch.heig.amt.gamification.entities;
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class BadgeRewardEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer rewardId;
    private Long timestamp;

    @ManyToOne
    private BadgeEntity badge;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ApplicationEntity application;

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

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }
}
