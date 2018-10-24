package ch.heig.amt.gamification.model;

public class Application {

    private int id;
    private String name;
    private String description;
    private String apiKey;
    private String apiSecret;
    private String user;

    public Application(int id, String name, String description, String apiKey, String apiSecret, String user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.user = user;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getUser() { return user; }

    public void setUser(String user) { this.user = user; }
}