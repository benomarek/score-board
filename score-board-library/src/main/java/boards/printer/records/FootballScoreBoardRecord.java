package boards.printer.records;

public class FootballScoreBoardRecord implements ScoreBoardRecord {

    private final String home;
    private final String away;
    private final int homeScore;
    private final int awayScore;

    public FootballScoreBoardRecord(String home, String away, int homeScore, int awayScore) {
        this.home = home;
        this.away = away;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    @Override
    public String printTeams() {
        return String.format("%s - %s", home, away);
    }

    @Override
    public String printScore() {
        return String.format("%s - %s", homeScore, awayScore);
    }
}
