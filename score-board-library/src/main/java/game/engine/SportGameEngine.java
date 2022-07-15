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

    private final List<Future<Boolean>> sportEventThreads = new ArrayList<>();
    private Future<Boolean> scoreBoardThread;

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
            sportEventThreads.add(submit);
        }

        scoreBoardThread = executorService.submit(scoreBoard::show);

        try {
            waitAllThreadsDone();
        } catch (ExecutionException | InterruptedException ignored) {
        }
    }

    private void waitAllThreadsDone() throws ExecutionException, InterruptedException {
        //wait for finish all
        for (Future<Boolean> future : sportEventThreads) {
                future.get();
        }
        scoreBoardThread.get();
        executorService.shutdownNow();
    }
}
