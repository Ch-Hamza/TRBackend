package tr.backend.message.request;

public class GUC {
    private GameForm game;
    private String username;

    public GUC() {
    }

    public GUC(GameForm game, String username) {
        this.game = game;
        this.username = username;
    }

    public GameForm getGame() {
        return game;
    }

    public void setGame(GameForm game) {
        this.game = game;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
