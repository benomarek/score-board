package messaging;

public interface MatchEventListener {
    void notifyUpdateScore(UpdateScoreMsg updateScoreMsg);
    void notifyEndOfMatch(MatchFinishedMsg matchFinishedMsg);
}
