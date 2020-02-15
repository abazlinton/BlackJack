import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;
    private Player currentPlayer;
    private int round;

    public Game() {
        this.players = new ArrayList<Player>();
        this.round = 1;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void nextPlayer(){
        this.round++;
        this.currentPlayer = this.players.get(this.round - 1);
    }

    private boolean isGameOver(){
        return this.round == this.players.size();
    }

    public void populateDeck(){
        this.deck = new Deck();
        this.deck.populate();
    }

    public void shuffleDeck(){
        this.deck.shuffle();
    }

    public void start(){
        this.currentPlayer = this.players.get(0);
        for (Player player : this.players){
            List<Card> cards = this.deck.takeCards(2);
            player.addCards(cards);
        }
    }

    public PlayerState getPlayerState(Player player){
        int playerScore = BlackJackScorer.getHandScore(player.getCards());
        Boolean has21 = playerScore == 21;
        Boolean hasBlackJack = has21 && player.getCards().size() == 2;
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

    public String getHandDescription(Player player){
        String handDescription = "";
        for (Card card : player.getCards()){
            handDescription += card.getCardDescription() + " ";
        }
        return handDescription;
    }

    public void runGame(){
        PlayerState currentPlayerState = this.getPlayerState(this.currentPlayer);
        int playerScore = BlackJackScorer.getHandScore(this.currentPlayer.getCards());
        if (currentPlayerState == PlayerState.ACTIVE){
            System.out.println(this.currentPlayer.getName() + ": " + String.valueOf(playerScore));
            BlackJackMove move = this.currentPlayer.getMove();
            if (move == BlackJackMove.TWIST){
                Card drawnCard = this.deck.takeCard();
                this.currentPlayer.addCard(drawnCard);
                System.out.println(getHandDescription(this.currentPlayer));
                runGame();
            } else if (move == BlackJackMove.STICK) {
                this.currentPlayer.setHasStuck(true);
                runGame();
            }
        } else {
            if (isGameOver()) {
                System.out.println(this.currentPlayer.getName() + ": " + String.valueOf(playerScore));
                return;
            } else {
                System.out.println(this.currentPlayer.getName() + ": " + String.valueOf(playerScore));
                nextPlayer();
                runGame();
            }
        }
    }

    public void huh(){
        System.out.println(BlackJackScorer.getHandScore(this.players.get(0).getCards()));
        System.out.println(this.getPlayerState(this.players.get(0)));
        System.out.println(this.getPlayerState(this.players.get(1)));
        System.out.println(BlackJackScorer.getHandScore(this.players.get(1).getCards()));

    }



}
