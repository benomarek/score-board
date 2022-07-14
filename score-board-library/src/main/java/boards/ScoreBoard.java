package boards;

import messaging.MatchEventListener;

public abstract class ScoreBoard {

    protected MatchEventListener updateScoreListener;

    public abstract boolean show();

    public MatchEventListener getUpdateScoreListener() {
        return updateScoreListener;
    }

}
