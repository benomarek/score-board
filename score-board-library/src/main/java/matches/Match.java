package matches;

import exceptions.MatchException;
import scores.Score;
import messaging.MatchEventListener;
import teams.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Match {

    protected final String matchId;
    protected List<MatchEventListener> matchEventListeners;
    protected Score score;
    protected final Team home;
    protected final Team away;

    protected Match(Team home, Team away) {

        if (home == null || away == null){
            throw  new MatchException(String.format("One of the team is null home=%s, away=%s", home, away));
        }

        if (home.equals(away)){
            throw  new MatchException("Home team and Away is the same.");
        }

        this.home = home;
        this.away = away;
        this.matchId = UUID.randomUUID().toString();
        this.matchEventListeners = new ArrayList<>();
        this.score = new Score();
    }

    public Score getScore() {
        return score;
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public String getMatchId() {
        return matchId;
    }

    public void registerUpdateMatchListeners(MatchEventListener matchEventListener){
        matchEventListeners.add(matchEventListener);
    }

    public void updateScore(Score score){
        this.score.setHome(score.getHome());
        this.score.setAway(score.getAway());
    }

    public abstract boolean start();

    public abstract boolean isFinished();
}
