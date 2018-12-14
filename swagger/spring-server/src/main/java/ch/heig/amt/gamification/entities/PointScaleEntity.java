package ch.heig.amt.gamification.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class PointScaleEntity implements Serializable {
    @Id
    private String pointScaleName;

    private int counter;

    public String getPointScaleName() {
        return pointScaleName;
    }

    public void setPointScaleName(String pointScaleName) {
        this.pointScaleName = pointScaleName;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
