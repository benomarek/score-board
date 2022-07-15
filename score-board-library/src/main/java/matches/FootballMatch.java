package matches;

import messaging.MatchEventListener;
import messaging.MatchFinishedMsg;
import messaging.UpdateScoreMsg;
import teams.FootballTeam;

import java.util.Random;

public class FootballMatch extends Match {

    private static final int DURATION_IN_MIN = 90;
    private final int totalDuration;
    private final Random random = new Random();
    private int currentMinutes = 0;

    public FootballMatch(FootballTeam home, FootballTeam away) {
        super(home, away);
        totalDuration = DURATION_IN_MIN + random.nextInt(5); // + stoppage time
    }

    @Override
    public boolean start() {

        for (currentMinutes = 0; currentMinutes < totalDuration; currentMinutes++) {
            try {
                //football game simulator
                Thread.sleep(random.nextInt(500));

                int rnd = random.nextInt(totalDuration);
                if (rnd == currentMinutes ||
                        rnd == (currentMinutes + random.nextInt(7)) ||
                        rnd == (currentMinutes - random.nextInt(7))) {

                    for (MatchEventListener matchEventListener : matchEventListeners) {
                        matchEventListener.notifyUpdateScore(
                                new UpdateScoreMsg(getMatchId(), rnd % 2 == 0 ? score.incrementHome() : score.incrementAway())
                        );
                    }
                }

            } catch (InterruptedException ignored) {

            }
        }
        for (MatchEventListener matchEventListener : matchEventListeners) {
            matchEventListener.notifyEndOfMatch(new MatchFinishedMsg(matchId));
        }

        return true;
    }

    @Override
    public boolean isFinished() {
        return currentMinutes == totalDuration;
    }

    @Override
    public String toString() {
        return "FootballMatch{" +
                "home=" + home +
                ", away=" + away +
                ", score=" + score +
                '}';
    }
}
