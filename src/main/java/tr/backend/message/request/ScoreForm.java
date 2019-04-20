package tr.backend.message.request;

public class ScoreForm {

    private String score;

    public ScoreForm() {
    }

    public ScoreForm(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
