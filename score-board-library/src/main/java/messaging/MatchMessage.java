package messaging;

public abstract class MatchMessage {

    protected final String matchId;

    protected MatchMessage(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }
}
