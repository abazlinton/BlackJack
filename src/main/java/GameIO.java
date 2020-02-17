import enums.BlackJackMove;
import enums.PlayerState;
import enums.WinState;
import java.util.List;
import java.util.Scanner;

public class GameIO {

    private Readable readable;
    private Writable writable;
    private Scanner scanner;

    public GameIO(Readable readable, Writable writable) {
        this.readable = readable;
        this.writable = writable;
        this.scanner = new Scanner(this.readable);
    }

    public void outputPlayerHand(Player player){
        this.writable.println(player.getName() + " has " + BlackJackScorer.getHandScore(player.getCards()));
    }

    public void outputDrawnCard(Card drawnCard){
        this.writable.println("Drew: " + drawnCard.getCardDescription());
    }

    public String getHandDescription(List<Card> cards){
        String handDescription = "";
        for (Card card : cards){
            handDescription += card.getCardDescription() + " ";
        }
        return handDescription;
    }

    public void outputPlayerDetails(Player player){
        int playerScore = BlackJackScorer.getHandScore(player.getCards());
        String output = player.getName() + ": " + getHandDescription(player.getCards()) + "- " + playerScore;
        this.writable.println(output);
    }

    public void outputDrama(Player player) {
        PlayerState playerState = Game.getPlayerState(player);
        if (playerState == PlayerState.BUST) {
            this.writable.println("*** " + player.getName() + " is Bust! ***");
        } else if (playerState == PlayerState.BLACKJACK) {
            this.writable.println("*** " + player.getName() + " has BlackJack! ***");
        } else if (playerState == PlayerState.SCORE21) {
            this.writable.println("*** " + player.getName() + " has 21! ***");
        }
    }

    public void outputOutcome(Player player){
        String playerName = player.getName();
        if (player.getWinState() == WinState.PLAYER){
            this.writable.println(playerName + " Won!");
        } else if (player.getWinState() == WinState.DEALER){
            this.writable.println(playerName + " Lost!");
        } else {
            this.writable.println(playerName + " Drew!");
        }
    }

    public BlackJackMove getPlayerMove(Player player){
        this.writable.println(player.getName() + " - What's your move? (S)tick (T)wist");
        String moveChoice = this.scanner.next().toUpperCase().substring(0, 1);
        if (moveChoice.equals("S")){
            return BlackJackMove.STICK;
        } else if (moveChoice.equals("T")){
            return BlackJackMove.TWIST;
        }
        return null;
    }
}
