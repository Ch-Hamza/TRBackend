package tr.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tr.backend.model.Team;
import tr.backend.repository.TeamRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teams")
public class TeamRestAPIs {

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Team> allTeams() {
        return teamRepository.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Team newTeam(@RequestBody Team newTeam) {
        return teamRepository.save(newTeam);

    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Team> getTeambyName(@PathVariable String name) {
        return teamRepository.findTeamByName(name);
    }

    @DeleteMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteTeambyName(@PathVariable String name) {
        teamRepository.deleteTeamByName(name);
    }
}
