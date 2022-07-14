package game.engine;

import exceptions.SportGameEngineException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SportGameEngineTest {

    @Test
    void initSportGameEngineWithoutMatches(){
        assertThrows(SportGameEngineException.class, () -> {
            new SportGameEngine(new ArrayList<>());
        });
    }
}
