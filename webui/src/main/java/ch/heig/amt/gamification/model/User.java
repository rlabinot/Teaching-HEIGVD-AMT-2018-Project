package ch.heig.amt.gamification.model;

public class User {
    private String name;
    private String mail;
    private String password;
    private boolean isAdmin;
    private boolean isActive;

    public User(String name, String mail, String password, boolean isAdmin, boolean isActive) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
