package tr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.backend.model.Score;
import tr.backend.model.ScoreDetails;
import tr.backend.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreDetailsRepository extends JpaRepository<ScoreDetails, Long> {
    List<ScoreDetails> findScoreDetailsByScoreId(Long score_id);

    @Query(value = "SELECT * FROM score LEFT JOIN game ON score.game_id=game.id LEFT JOIN users ON score.user_id=users.id WHERE users.username=?1 AND game.id=?2",nativeQuery = true)
    List<ScoreDetails> findScoreDetailsByUserIdAndGameId(String username, Long game_id);

    Optional<ScoreDetails> findScoreDetailsByScoreAndUser(Score score, User user);
    Optional<ScoreDetails> findScoreDetailsByUser(User user);
}
