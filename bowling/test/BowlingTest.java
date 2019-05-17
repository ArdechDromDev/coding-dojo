import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BowlingTest {

    @Test
    void shouldReturnZeroWhenGameStart() {
        Partie partie = new Partie();
        assertEquals(0, partie.getScore());
    }

    @Test
    void shouldReturn5WhenGameFirstTurnScore5() {
        Partie partie = new Partie(new Manche(2, 3));
        assertEquals(5, partie.getScore());
    }

    @Test
    void shouldReturn10WhenFirstTurnIsSpare(){
        Partie partie = new Partie(new Manche(5, 5),new Manche(0));
        assertEquals(10, partie.getScore());
    }

    @Test
    void shouldReturn12WhenFirstTurnIsSpare(){
        Partie partie = new Partie(new Manche(5, 5),new Manche(1));
        assertEquals(12, partie.getScore());
    }
    @Test
    void shouldReturn16WhenFirstTurnIsStrike(){
        Partie partie = new Partie(new Manche(10),new Manche(1,  2));
        assertEquals(16, partie.getScore());
    }

    @Test
    void shouldReturn0WhenOnlyOneStrike(){
        Partie partie = new Partie(new Manche(10));
        assertEquals(0, partie.getScore());
    }
}