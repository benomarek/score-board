package boards;

import boards.printer.ScoreBoardPrinter;
import boards.printer.console.ConsolePrinter;
import boards.printer.records.FootballScoreBoardRecord;
import boards.printer.records.ScoreBoardRecord;
import matches.Match;
import messaging.MatchFinishedMsg;
import messaging.MatchEventListener;
import messaging.UpdateScoreMsg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FootballScoreBoard extends ScoreBoard {

    ScoreBoardPrinter scoreBoardPrinter;
    private final Map<String, Match> allMatchesById = new HashMap<>();
    private final Map<String, Match> liveMatchesById = new HashMap<>();

    public FootballScoreBoard(List<Match> matches) {
        this.updateScoreListener = new FootballEventListener();

        final Map<String, Match> newMatchesById = matches.stream()
                .collect(Collectors.toMap(Match::getMatchId, match -> match));

        allMatchesById.putAll(newMatchesById);
        liveMatchesById.putAll(newMatchesById);
        scoreBoardPrinter = ConsolePrinter.instanceByOs();
    }

    @Override
    public boolean show() {

        if (liveMatchesById.isEmpty()) {
            scoreBoardPrinter.printTotalScore(getAllMatchesToRecordsSorted());
            return true;
        }

        scoreBoardPrinter.printLiveScore(getLiveMatchesBoardRecords());
        return true;
    }

    private List<ScoreBoardRecord> getLiveMatchesBoardRecords() {
        return liveMatchesById.values().stream()
                .map(m -> new FootballScoreBoardRecord(m.getHome().getName(),
                        m.getAway().getName(), m.getScore().getHome(), m.getScore().getAway()))
                .collect(Collectors.toList());
    }

    private List<ScoreBoardRecord> getAllMatchesToRecordsSorted() {
        return allMatchesById.values().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getScore().getTotal(), o1.getScore().getTotal()))
                .map(m -> new FootballScoreBoardRecord(m.getHome().getName(),
                        m.getAway().getName(), m.getScore().getHome(), m.getScore().getAway()))
                .collect(Collectors.toList());
    }


    class FootballEventListener implements MatchEventListener {

        @Override
        public void notifyUpdateScore(UpdateScoreMsg updateScoreMsg) {
            liveMatchesById.get(updateScoreMsg.getMatchId())
                    .updateScore(updateScoreMsg.getScore());

            show();
        }

        @Override
        public void notifyEndOfMatch(MatchFinishedMsg matchFinishedMsg) {
            liveMatchesById.remove(matchFinishedMsg.getMatchId());
            show();
        }
    }

}
