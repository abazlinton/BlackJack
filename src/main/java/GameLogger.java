import enums.PlayerState;
import enums.WinState;

import java.util.List;

public class GameLogger {

    public static void outputPlayerHand(Player player){
        System.out.println(player.getName() + " has " + BlackJackScorer.getHandScore(player.getCards()));
    }

    public static void outputDrawnCard(Card drawnCard){
        System.out.println("Drew: " + drawnCard.getCardDescription());
    }

    public static String getHandDescription(List<Card> cards){
        String handDescription = "";
        for (Card card : cards){
            handDescription += card.getCardDescription() + " ";
        }
        return handDescription;
    }

    public static void outputPlayerDetails(Player player){
        int playerScore = BlackJackScorer.getHandScore(player.getCards());
        String output = player.getName() + ": " + getHandDescription(player.getCards()) + "- " + playerScore;
        System.out.println(output);
    }

    public static void outputDrama(Player player) {
        PlayerState playerState = Game.getPlayerState(player);
        if (playerState == PlayerState.BUST) {
            System.out.println("*** " + player.getName() + " is Bust! ***");
        } else if (playerState == PlayerState.BLACKJACK) {
            System.out.println("*** " + player.getName() + " has BlackJack! ***");
        } else if (playerState == PlayerState.SCORE21) {
            System.out.println("*** " + player.getName() + " has 21! ***");
        }
    }

    public static void outputOutcome(Player player){
        String playerName = player.getName();
        if (player.getWinState() == WinState.PLAYER){
            System.out.println(playerName + " Won!");
        } else if (player.getWinState() == WinState.DEALER){
            System.out.println(playerName + " Lost!");
        } else {
            System.out.println(playerName + " Drew!");
        }
    }
}
