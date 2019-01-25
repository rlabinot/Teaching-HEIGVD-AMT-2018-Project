package ch.heig.amt.gamification.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PointScaleEntity implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer pointScaleId;
    private String pointScaleName;

    @ManyToOne
    private ApplicationEntity application;

    public Integer getPointScaleId() { return pointScaleId; }

    public void setPointScaleId(Integer pointScaleId) { this.pointScaleId = pointScaleId; }

    public String getPointScaleName() {
        return pointScaleName;
    }

    public void setPointScaleName(String pointScaleName) {
        this.pointScaleName = pointScaleName;
    }
    
    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

}
