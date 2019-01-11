package ch.heig.amt.gamification.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class BadgeRewardEntity extends RewardEntity {
    @ManyToOne
    private BadgeEntity badge;

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }
}
