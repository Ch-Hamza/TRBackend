package tr.backend.message.request;

import java.util.List;

public class GameForm {

    private String competition;
    private String state;
    private List<TeamForm> teams;

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TeamForm> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamForm> teams) {
        this.teams = teams;
    }
}
