package matches;

import exceptions.MatchException;
import messaging.MatchFinishedMsg;
import messaging.MatchEventListener;
import messaging.UpdateScoreMsg;
import teams.FootballTeam;

import java.util.Random;

public class FootballMatch extends Match {

    private static final int DurationInMin = 90;

    private int currentMinutes = 0;

    public FootballMatch(FootballTeam home, FootballTeam away) {
        super(home, away);
    }

    @Override
    public boolean start() {

        final Random random = new Random();

        int totalDuration = DurationInMin + random.nextInt(5); // + stoppage time

        for (currentMinutes = 0; currentMinutes < totalDuration; currentMinutes++) {
            try {

                //basic alg. to simulate football game
                Thread.sleep(random.nextInt(500));

                int rnd = random.nextInt(totalDuration);
                if (rnd == currentMinutes ||
                        rnd == (currentMinutes + random.nextInt(7)) ||
                        rnd == (currentMinutes - random.nextInt(7))) {

                    for (MatchEventListener updateScoreListener : matchEventListeners) {
                        updateScoreListener.notifyUpdateScore(new UpdateScoreMsg(getMatchId(), rnd % 2 == 0 ? score.incrementHome() : score.incrementAway()));
                    }
                }

            } catch (InterruptedException ignored) {

            }
        }
        for (MatchEventListener updateScoreListener : matchEventListeners) {
            updateScoreListener.notifyEndOfMatch(new MatchFinishedMsg(matchId));
        }

        return true;
    }

    @Override
    public boolean isFinished() {
        return currentMinutes == DurationInMin;
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
