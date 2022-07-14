package matches;

import exceptions.MatchException;
import org.junit.jupiter.api.Test;
import teams.FootballTeam;

import static org.junit.jupiter.api.Assertions.assertThrows;

class MatchTests {

    @Test
    void sameTeamsInMatchTest() {
        FootballTeam slovakiaTeam = new FootballTeam("Slovakia");

        assertThrows(MatchException.class, () -> {
            new FootballMatch(slovakiaTeam, slovakiaTeam);
        });
    }
}
