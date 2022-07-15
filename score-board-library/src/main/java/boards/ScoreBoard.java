package boards;

import messaging.MatchEventListener;

public abstract class ScoreBoard {

    protected MatchEventListener matchEventListener;

    public abstract boolean show();

    public MatchEventListener getMatchEventListener() {
        return matchEventListener;
    }

}
