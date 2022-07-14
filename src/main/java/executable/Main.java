package executable;

import game.engine.SportGameEngine;
import matches.FootballMatch;
import matches.Match;
import teams.FootballTeam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        FootballTeam mexico = new FootballTeam("Mexico");
        FootballTeam canada = new FootballTeam("Canada");

        FootballTeam spain = new FootballTeam("Spain");
        FootballTeam brazil = new FootballTeam("Brazil");

        FootballTeam german = new FootballTeam("German");
        FootballTeam france = new FootballTeam("France");

        FootballTeam uruguay = new FootballTeam("Uruguay");
        FootballTeam italy = new FootballTeam("Italy");

        FootballTeam argentina = new FootballTeam("Argentina");
        FootballTeam australia = new FootballTeam("Australia");

        List<FootballTeam> teams = new ArrayList<>(
                List.of(mexico, canada, spain, brazil, german, france, uruguay, italy, argentina, australia)
        );
        Collections.shuffle(teams);

        final List<Match> matches = new ArrayList<>();

        if (teams.size() % 2 != 0){
            System.err.println("Size of teams must be an even number.");
            return;
        }

        for (int i = 0; i < teams.size(); ) {
            matches.add(new FootballMatch(teams.get(i++), teams.get(i++)));
        }

        SportGameEngine gameEngine = new SportGameEngine(matches);
        gameEngine.run();

    }
}
