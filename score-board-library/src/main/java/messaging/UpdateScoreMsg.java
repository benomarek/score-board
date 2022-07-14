package messaging;

import scores.Score;

public class UpdateScoreMsg extends MatchMessage {

    private final Score score;

    public UpdateScoreMsg(String matchId, Score newScore) {
        super(matchId);
        this.score = newScore;
    }

    public Score getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "UpdateScoreMsg{" +
                "matchId='" + matchId + '\'' +
                ", score=" + score +
                '}';
    }
}
