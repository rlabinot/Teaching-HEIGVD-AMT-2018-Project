package ch.heig.amt.gamification.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class RuleEntity implements Serializable {
    @Id
    private int ruleId;

    private String ruleName;
    private int badgeId;
    private String eventTrigger;

    @ManyToOne
    private ApplicationEntity application;
    @ManyToOne
    private BadgeEntity badge;
    @ManyToOne
    private PointScaleEntity pointScale;
    @ManyToOne
    private RewardEntity reward;


    public int getRuleId() { return ruleId; }

    public void setRuleId(int ruleId) { this.ruleId = ruleId; }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public String getEventTrigger() {
        return eventTrigger;
    }

    public void setEventTrigger(String eventTrigger) {
        this.eventTrigger = eventTrigger;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public BadgeEntity getBadge() {
        return badge;
    }

    public void setBadge(BadgeEntity badge) {
        this.badge = badge;
    }

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }

    public RewardEntity getReward() {
        return reward;
    }

    public void setReward(RewardEntity reward) {
        this.reward = reward;
    }

}
