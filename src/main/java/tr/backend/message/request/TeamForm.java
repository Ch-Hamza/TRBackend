package tr.backend.message.request;

public class TeamForm {

    private String name;
    private String image;
    private String competition;
    private ScoreForm score;

    public TeamForm() {
    }

    public TeamForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public ScoreForm getScore() {
        return score;
    }

    public void setScore(ScoreForm score) {
        this.score = score;
    }
}
