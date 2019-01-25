package ch.heig.amt.gamification.entities;

import javax.persistence.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer userId;

    @ManyToOne
    private ApplicationEntity application;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }
}
