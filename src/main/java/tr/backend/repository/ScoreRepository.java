package tr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.backend.model.Game;
import tr.backend.model.Score;
import tr.backend.model.Team;

import java.util.List;

@Repository
public interface ScoreRepository  extends JpaRepository<Score, Long> {
    List<Score> findScoreByGame(Game g);
    Score findScoreByGameAndTeamId(Game g, Team team);
}
