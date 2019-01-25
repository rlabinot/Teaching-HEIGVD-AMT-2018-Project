package ch.heig.amt.gamification.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PointScaleRewardEntity implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int rewardId;
    private long timestamp;
    private String reason;

    @ManyToOne
    private PointScaleEntity pointScale;
    private int amount = 0;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ApplicationEntity application;

    @ManyToOne
    private EventEntity event;


    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount += amount;
    }
}

