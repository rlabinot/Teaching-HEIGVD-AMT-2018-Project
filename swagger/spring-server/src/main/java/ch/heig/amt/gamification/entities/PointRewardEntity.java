package ch.heig.amt.gamification.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PointRewardEntity extends RewardEntity {
    @ManyToOne
    private PointScaleEntity pointScale;

    public PointScaleEntity getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScaleEntity pointScale) {
        this.pointScale = pointScale;
    }
}

