import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;
    private Player dealer;

    public Game() {
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

    public PlayerState getPlayerState(Player player){
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

    public void runTurn(Player player) {
        while (this.getPlayerState(player) == PlayerState.ACTIVE){
            outputPlayerDetails(player);
            BlackJackMove move = player.getMove();
            if (move == BlackJackMove.TWIST) {
                Card drawnCard = this.deck.takeCard();
                player.addCard(drawnCard);
                System.out.println("Drew: " + drawnCard.getCardDescription());
            } else if (move == BlackJackMove.STICK) {
                player.setHasStuck(true);
            }
        }
        outputPlayerDetails(player);
        if (this.getPlayerState(player) == PlayerState.BUST){
            System.out.println("*** " + player.getName() + " is Bust! ***");
        }
        if (this.getPlayerState(player) == PlayerState.BLACKJACK){
            System.out.println("*** " + player.getName() + " has BlackJack! ***");
        } else if (this.getPlayerState(player) == PlayerState.SCORE21) {
            System.out.println("***" + player.getName() + " has 21! ***");
        }
    }

    public void runDealerTurn(){
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
        outputPlayerDetails(dealer);
    }

    public void runGame(){
        for (Player player : this.players){
            runTurn(player);
        }
        runDealerTurn();
    }


    public void summarizeGame(){
        System.out.println("Dealer has " + BlackJackScorer.getHandScore(this.dealer.getCards()));
        PlayerState dealerState = this.getPlayerState(dealer);
        for (Player player : this.players){
            PlayerState playerState = this.getPlayerState(player);
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
            System.out.println(player.getName() + " has " + BlackJackScorer.getHandScore(player.getCards()));
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



}
