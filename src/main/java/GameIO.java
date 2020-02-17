import enums.BlackJackMove;
import enums.PlayerState;
import enums.WinState;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class GameIO {

    private InputStream inputStream;

    public GameIO(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void outputPlayerHand(Player player){
        System.out.println(player.getName() + " has " + BlackJackScorer.getHandScore(player.getCards()));
    }

    public void outputDrawnCard(Card drawnCard){
        System.out.println("Drew: " + drawnCard.getCardDescription());
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
        System.out.println(output);
    }

    public void outputDrama(Player player) {
        PlayerState playerState = Game.getPlayerState(player);
        if (playerState == PlayerState.BUST) {
            System.out.println("*** " + player.getName() + " is Bust! ***");
        } else if (playerState == PlayerState.BLACKJACK) {
            System.out.println("*** " + player.getName() + " has BlackJack! ***");
        } else if (playerState == PlayerState.SCORE21) {
            System.out.println("*** " + player.getName() + " has 21! ***");
        }
    }

    public void outputOutcome(Player player){
        String playerName = player.getName();
        if (player.getWinState() == WinState.PLAYER){
            System.out.println(playerName + " Won!");
        } else if (player.getWinState() == WinState.DEALER){
            System.out.println(playerName + " Lost!");
        } else {
            System.out.println(playerName + " Drew!");
        }
    }

    public BlackJackMove getPlayerMove(Player player){
        Scanner scanner = new Scanner(this.inputStream);
        System.out.println(player.getName() + " - What's your move? (S)tick (T)wist");
        String moveChoice = scanner.next().toUpperCase().substring(0, 1);
        if (moveChoice.equals("S")){
            return BlackJackMove.STICK;
        } else if (moveChoice.equals("T")){
            return BlackJackMove.TWIST;
        }
        return null;
    }
}
