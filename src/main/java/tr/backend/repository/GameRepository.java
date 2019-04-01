package tr.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.backend.model.Game;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findTopByOrderByIdDesc();
}
