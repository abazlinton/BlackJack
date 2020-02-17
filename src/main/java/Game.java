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

    public void runTurn(Player player) {
        while (this.getPlayerState(player) == PlayerState.ACTIVE){
            int playerScore = BlackJackScorer.getHandScore(player.getCards());
            System.out.println(player.getName() + ": " + String.valueOf(playerScore));
            System.out.println(this.getHandDescription(player.getCards()));
            BlackJackMove move = player.getMove();
            if (move == BlackJackMove.TWIST) {
                Card drawnCard = this.deck.takeCard();
                player.addCard(drawnCard);
                System.out.println("Drew: " + drawnCard.getCardDescription());
            } else if (move == BlackJackMove.STICK) {
                player.setHasStuck(true);
            }
        }
    }

    public void runPlayerTurn(){
        while (BlackJackScorer.getHandScore(dealer.getCards()) < 17) {
            dealer.addCard(this.deck.takeCard());
        }
        if (BlackJackScorer.getHandScore(dealer.getCards()) < 21 ){
            dealer.setHasStuck(true);
        }
    }

    public void runGame(){
        for (Player player : this.players){
            runTurn(player);
        }
        runPlayerTurn();
    }


    public void summarizeGame(){
        System.out.println("Dealer has: " + BlackJackScorer.getHandScore(this.dealer.getCards()));
        System.out.println(getHandDescription(this.dealer.getCards()));
        if (this.getPlayerState(dealer) == PlayerState.BLACKJACK){
            System.out.println("BlackJack!!");
        }
        PlayerState dealerState = this.getPlayerState(dealer);
        for (Player player : this.players){
            PlayerState playerState = this.getPlayerState(player);
            if (dealerState == PlayerState.BUST){
                if (playerState != PlayerState.BUST){
                    System.out.println(player.getName() + "Wins");
                }
            }
        }
    }



}
