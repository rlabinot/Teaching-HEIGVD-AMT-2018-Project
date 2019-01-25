package ch.heig.amt.gamification.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PointScaleRewardEntity implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer rewardId;
    private Long timestamp;

    @ManyToOne
    private PointScaleEntity pointScale;
    private Integer amount = 0;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount += amount;
    }
}

