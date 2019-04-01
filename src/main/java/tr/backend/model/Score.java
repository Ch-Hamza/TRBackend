package tr.backend.model;

import javax.persistence.*;

@Entity
@Table(name="score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String score;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Team.class)
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
