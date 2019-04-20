package tr.backend.model;

import javax.persistence.*;

@Entity
@Table(name="score_details")
public class ScoreDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scoreValue;
    private String scoreDetails;

    @OneToOne(targetEntity = Score.class)
    private Score score;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(String scoreValues) {
        this.scoreValue = scoreValues;
    }

    public String getScoreDetails() {
        return scoreDetails;
    }

    public void setScoreDetails(String scoreDetails) {
        this.scoreDetails = scoreDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
