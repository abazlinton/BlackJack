import java.io.InputStreamReader;

public class Runner {


    public static void main(String[] args) {
        Player testPlayer = new Player("Alex");
        Player testPlayer2 = new Player("Bob");
        Readable inputStreamReader = new InputStreamReader(System.in);
        Writable writable = new SystemOutWrapper();
        GameIO gameIO = new GameIO(inputStreamReader, writable);
        Game game = new Game(gameIO);
        game.addPlayer(testPlayer);
        game.addPlayer(testPlayer2);
        game.populateDeck();
        game.shuffleDeck();
        game.start();
        game.runGame();
        game.summarizeGame();
    }
}
