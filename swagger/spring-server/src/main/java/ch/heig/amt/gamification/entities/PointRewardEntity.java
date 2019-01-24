package ch.heig.amt.gamification.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PointRewardEntity extends RewardEntity {
    @ManyToOne
    private PointScaleEntity pointScale;
    private int amount;

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
        this.amount = amount;
    }
}

