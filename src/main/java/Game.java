import enums.BlackJackMove;
import enums.PlayerState;
import enums.WinState;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;
    private Player dealer;
    private GameIO gameIO;

    public Game(GameIO gameIO) {
        this.gameIO = gameIO;
        this.players = new ArrayList<Player>();
        this.dealer = new Player("Dealer");
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void populateDeck(){
        this.deck = new Deck();
        this.deck.populate();
    }

    public void shuffleDeck(){
        this.deck.shuffle();
    }

    public void start(){
        for (Player player : this.players){
            List<Card> cards = this.deck.takeCards(2);
            player.addCards(cards);
        }
        this.dealer.addCards(this.deck.takeCards(2));
    }

    public static PlayerState getPlayerState(Player player){
        int playerScore = BlackJackScorer.getHandScore(player.getCards());
        boolean has21 = playerScore == 21;
        boolean hasBlackJack = has21 && player.getCards().size() == 2;
        if (hasBlackJack){
            return PlayerState.BLACKJACK;
        } else if (has21) {
            return PlayerState.SCORE21;
        } else if (player.getHasStuck()) {
            return PlayerState.STUCK;
        } else if (playerScore > 21) {
            return PlayerState.BUST;
        } else {
            return PlayerState.ACTIVE;
        }
    }

    private void runPlayerTurn(Player player) {
        while (Game.getPlayerState(player) == PlayerState.ACTIVE){
            this.gameIO.outputPlayerDetails(player);
            BlackJackMove move = this.gameIO.getPlayerMove(player);
            if (move == BlackJackMove.TWIST) {
                Card drawnCard = this.deck.takeCard();
                player.addCard(drawnCard);
                this.gameIO.outputDrawnCard(drawnCard);
            } else if (move == BlackJackMove.STICK) {
                player.setHasStuck(true);
            }
        }
        this.gameIO.outputPlayerDetails(player);
        this.gameIO.outputDrama(player);
    }

    private void runDealerTurn(){
        boolean dealerShouldPlay = false;
        for (Player player : this.players){
            if (getPlayerState(player) != PlayerState.BUST){
                dealerShouldPlay = true;
            }
        }
        if (!dealerShouldPlay) return;
        while (BlackJackScorer.getHandScore(dealer.getCards()) < 17) {
            dealer.addCard(this.deck.takeCard());
        }
        if (BlackJackScorer.getHandScore(dealer.getCards()) < 21 ){
            dealer.setHasStuck(true);
        }
        this.gameIO.outputPlayerDetails(dealer);
        this.gameIO.outputDrama(dealer);
    }

    public void runGame(){
        for (Player player : this.players){
            runPlayerTurn(player);
        }
        runDealerTurn();
    }

    public void summarizeGame(){
        this.gameIO.outputPlayerHand(this.dealer);
        PlayerState dealerState = Game.getPlayerState(dealer);
        for (Player player : this.players){
            PlayerState playerState = Game.getPlayerState(player);
            if (playerState == PlayerState.BUST){
                player.setWinState(WinState.DEALER);
            } else if (dealerState.ordinal() <  playerState.ordinal()){
                player.setWinState(WinState.DEALER);
            } else if (playerState.ordinal() < dealerState.ordinal()){
                player.setWinState(WinState.PLAYER);
            } else if (playerState == dealerState && playerState == PlayerState.STUCK){
                int playerScore = BlackJackScorer.getHandScore(player.getCards());
                int dealerScore = BlackJackScorer.getHandScore(this.dealer.getCards());
                if (playerScore > dealerScore){
                    player.setWinState(WinState.PLAYER);
                } else if (dealerScore > playerScore) {
                    player.setWinState(WinState.DEALER);
                } else {
                    player.setWinState(WinState.DRAW);
                }
            }
            this.gameIO.outputPlayerHand(player);
            this.gameIO.outputOutcome(player);
        }
    }
}
