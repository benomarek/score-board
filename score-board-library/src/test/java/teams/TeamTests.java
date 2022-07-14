package teams;

import exceptions.TeamException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTests {

    @Test
    void teamWithoutNameTest(){

        assertThrows(TeamException.class, () -> {
            new FootballTeam("");
        });

        assertThrows(TeamException.class, () -> {
           new FootballTeam(null);
        });
    }
}
