package boards;

import exceptions.FootballScoreBoardException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FootballScoreBoardTest {

    @Test
    void initFootballScoreBoardMatches(){
        assertThrows(FootballScoreBoardException.class, () -> {
            new FootballScoreBoard(new ArrayList<>());
        });
    }
}
