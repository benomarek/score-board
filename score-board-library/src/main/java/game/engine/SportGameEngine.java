package game.engine;

import boards.FootballScoreBoard;
import boards.ScoreBoard;
import exceptions.SportGameEngineException;
import matches.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SportGameEngine {

    private final List<Match> matches;
    private final ScoreBoard scoreBoard;
    private final ExecutorService executorService;

    List<Future<Boolean>> matchesThreads = new ArrayList<>();

    public SportGameEngine(List<Match> matches) {

        if (matches == null || matches.isEmpty()) {
            throw new SportGameEngineException("Minimum one match is required");
        }

        this.matches = matches;
        this.scoreBoard = new FootballScoreBoard(matches);
        this.executorService = Executors.newFixedThreadPool(matches.size() + 1); //+1 thread for scoreboard
    }

    public SportGameEngine(Match... match) {
        this(List.of(match));
    }

    public void run() {

        for (Match match : matches) {
            match.registerUpdateMatchListeners(scoreBoard.getMatchEventListener());
            final Future<Boolean> submit = executorService.submit(match::start);
            matchesThreads.add(submit);
        }

        matchesThreads.add(executorService.submit(scoreBoard::show));

        waitForAllMatchesFinish();
    }

    private void waitForAllMatchesFinish() {
        //wait for finish all
        for (Future<Boolean> future : matchesThreads) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }

        executorService.shutdownNow();
    }
}
