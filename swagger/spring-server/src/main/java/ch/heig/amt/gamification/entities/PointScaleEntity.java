package ch.heig.amt.gamification.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PointScaleEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int pointScaleId;
    private String pointScaleName;
    private int counter;

    @ManyToOne
    private ApplicationEntity application;

    public int getPointScaleId() { return pointScaleId; }

    public void setPointScaleId(int pointScaleId) { this.pointScaleId = pointScaleId; }

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

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

}
