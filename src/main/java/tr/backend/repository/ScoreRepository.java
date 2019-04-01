package tr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.backend.model.Game;
import tr.backend.model.Score;

import java.util.List;

public interface ScoreRepository  extends JpaRepository<Score, Long> {
    List<Score> findScoreByGame(Game g);
}
