package tr.backend.message.request;

public class ScoreDetails {
    private String value;
    private String details;
    private String username;

    public ScoreDetails() {
    }

    public ScoreDetails(String details, String username, String value) {
        this.details = details;
        this.username = username;
        this.value = value;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
