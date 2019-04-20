package tr.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.backend.message.request.*;
import tr.backend.model.Game;
import tr.backend.model.Score;
import tr.backend.model.Team;
import tr.backend.repository.*;

import javax.transaction.Transactional;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/games")
public class GameRestAPIs {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreDetailsRepository scoreDetailsRepository;

    /*@GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<GameForm>> allGames() {

        try {
            List<GameForm> _games = new ArrayList<>();
            List<TeamForm> teams = new ArrayList<>();
            List<Game> games = new ArrayList<>(gameRepository.findAll());
            if(games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            games.forEach(game -> {
                GameForm _game = new GameForm();
                _game.setCompetition(game.getCompetition());
                _game.setState(game.getState());

                List<Score> scores = scoreRepository.findScoreByGame(game);
                scores.forEach(score -> {
                    TeamForm t = new TeamForm();
                    t.setName(score.getTeam().getName());
                    t.setCompetition(score.getTeam().getCompetition());
                    t.setImage(score.getTeam().getImage());

                    ScoreForm sf = new ScoreForm();
                    sf.setScore(score.getScore());

                    ScoreDetails sd = new ScoreDetails();
                    if(score.getScore() != null) {
                        tr.backend.model.ScoreDetails s = scoreDetailsRepository.findScoreDetailsByScoreId(score.getId());
                        sd.setDetails(s.getScoreDetails());
                        sd.setUsername(s.getUser().getUsername());
                        sd.setValue(s.getScoreValue());
                    }
                    else {
                        sd.setDetails("");
                        sd.setUsername("");
                        sd.setValue("");
                    }

                    sf.setScoreDetails(sd);
                    t.setScore(sf);
                    teams.add(t);
                });

                _game.setTeams(teams);
                _games.add(_game);
            });
            return new ResponseEntity<>(_games, HttpStatus.OK);

        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    /*@GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GameForm> getGameById(@PathVariable("id") long id) {

        Optional<Game> game = gameRepository.findById(id);
        List<TeamForm> strTeams = new ArrayList<>();
        if(game.isPresent()) {

            GameForm _game = new GameForm();
            _game.setCompetition(game.get().getCompetition());
            _game.setState(game.get().getState());

            List<Score> scores = scoreRepository.findScoreByGame(game.get());
            scores.forEach(score -> {
                TeamForm t = new TeamForm();
                t.setName(score.getTeam().getName());
                t.setCompetition(score.getTeam().getCompetition());
                t.setImage(score.getTeam().getImage());

                ScoreForm sf = new ScoreForm();
                sf.setScore(score.getScore());

                ScoreDetails sd = new ScoreDetails();
                tr.backend.model.ScoreDetails s = scoreDetailsRepository.findScoreDetailsByScoreId(score.getId());
                if(s != null) {
                    sd.setDetails(s.getScoreDetails());
                    sd.setUsername(s.getUser().getUsername());
                    sd.setValue(s.getScoreValue());
                }
                else {
                    sd.setDetails("");
                    sd.setUsername("");
                    sd.setValue("");
                }

                sf.setScoreDetails(sd);
                t.setScore(sf);

                strTeams.add(t);
            });
            _game.setTeams(strTeams);

            return new ResponseEntity<>(_game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @GetMapping("/current")
    @PreAuthorize("hasRole('ADMIN') or hasRole('JURY') or asRole('JUDGE')")
    public ResponseEntity<GameForm> getCurrentGame() {

        Optional<Game> game = gameRepository.findTopByOrderByIdDesc();
        List<TeamForm> strTeams = new ArrayList<>();
        if(game.isPresent()) {
            GameForm _game = new GameForm();
            _game.setCompetition(game.get().getCompetition());
            _game.setState(game.get().getState());

            List<Score> scores = scoreRepository.findScoreByGame(game.get());
            scores.forEach(score -> {
                TeamForm t = new TeamForm();
                t.setName(score.getTeam().getName());
                t.setCompetition(score.getTeam().getCompetition());
                t.setImage(score.getTeam().getImage());

                ScoreForm sf = new ScoreForm();
                sf.setScore((score.getScore()));
                t.setScore(sf);
                strTeams.add(t);
            });
            _game.setTeams(strTeams);

            return new ResponseEntity<>(_game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Game> newGame(@RequestBody GameForm newGame) {

        Game game = new Game(newGame.getCompetition(), newGame.getState());

        Set<Score> scores = new HashSet<>();
        List<TeamForm> strTeams = newGame.getTeams();

        strTeams.forEach(team -> {
            Team t = teamRepository.findTeamByName(team.getName())
                    .orElseThrow(() -> new RuntimeException("Fail! -> Cause: Team doesn't exist."));

            Score _score = new Score();
            _score.setGame(game);
            _score.setScore("0");
            _score.setTeam(t);
            scores.add(_score);
        });

        gameRepository.save(game);
        scores.forEach(score -> scoreRepository.save(score));

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<GameForm> updateScore(@RequestBody GameForm game) {

        Optional<Game> g = gameRepository.findTopByOrderByIdDesc();
        if(g.isPresent()) {
            Game _g = g.get();

            List<Score> scores = scoreRepository.findScoreByGame(_g);
            scores.forEach(score -> {
                game.getTeams().forEach(team -> {
                    if(score.getTeam().getName().equals(team.getName())) {
                        int oldScore = Integer.parseInt(score.getScore());
                        int newScore = oldScore + Integer.parseInt(team.getScore().getScore());
                        //if we want moy newscore/=nbrjury
                        score.setScore(Integer.toString(newScore));
                    }
                });
            });
            gameRepository.save(_g);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping("/state")
    public ResponseEntity<GameForm> updateState(@RequestBody GameForm game) {

        Optional<Game> g = gameRepository.findTopByOrderByIdDesc();
        if(g.isPresent()) {
            Game _g = g.get();

            _g.setState(game.getState());

            gameRepository.save(_g);
            return new ResponseEntity<>(game, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<HttpStatus> deleteGameById(@PathVariable("id") Long id) {

        try {
            gameRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}