package ch.heig.amt.gamification.entities;

import javax.persistence.*;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int userId;

    @ManyToOne
    private ApplicationEntity application;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }
}
