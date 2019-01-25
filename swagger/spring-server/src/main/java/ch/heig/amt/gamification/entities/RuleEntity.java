package ch.heig.amt.gamification.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RuleEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer ruleId;

    private String ruleName;
    private String eventTrigger;
    private Integer amount;

    @ManyToOne
    private ApplicationEntity application;
    @ManyToOne
    private BadgeEntity badge;
    @ManyToOne
    private PointScaleEntity pointScale;


    public Integer getRuleId() { return ruleId; }

    public void setRuleId(Integer ruleId) { this.ruleId = ruleId; }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
