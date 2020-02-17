import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {



    @Test
    public void test_full_game_stick_lost(){
        Player testPlayer = new Player("Alex");
        // Alex will Stick immediately
        Readable readable = new StringReader("S");
        Writable writable = new CaptureOutput();
        GameIO gameIO = new GameIO(readable, writable);
        Game game = new Game(gameIO);
        game.addPlayer(testPlayer);
        game.populateDeck();
        game.start();
        // Alex has Ah 2h - 13
        // Dealer has 3h 4h 5h 6h - 18
        game.runGame();
        game.summarizeGame();
        CaptureOutput captureOutput = (CaptureOutput) writable;
        String lastLine = captureOutput.getLines().get(captureOutput.getLines().size() - 1 );
        assertTrue(lastLine.contains("Alex Lost!"));
    }

    @Test
    public void test_full_game_twist_won(){
        Player testPlayer = new Player("Alex"); 
        Readable readable = new StringReader("T T S");
        Writable writable = new CaptureOutput();
        GameIO gameIO = new GameIO(readable, writable);
        Game game = new Game(gameIO);
        game.addPlayer(testPlayer);
        game.populateDeck();
        game.start();
        // Alex has Ah 2h 3h 4h - 20
        // Dealer has 5h 6h 7h - 18
        game.runGame();
        game.summarizeGame();
        CaptureOutput captureOutput = (CaptureOutput) writable;
        String lastLine = captureOutput.getLines().get(captureOutput.getLines().size() - 1 );
        assertEquals("Alex Won!", lastLine);
    }

}
