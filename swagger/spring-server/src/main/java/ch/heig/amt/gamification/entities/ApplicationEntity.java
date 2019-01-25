package ch.heig.amt.gamification.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ApplicationEntity implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer applicationId;
    private String applicationName;

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}
