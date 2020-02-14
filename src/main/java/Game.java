import java.util.ArrayList;
import java.util.List;

public class Game {

    private Deck deck;
    private List<Player> players;
    private Player currentPlayer;

    public Game(Deck deck) {
        this.deck = deck;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void prepare(){
        this.deck = new Deck();
        this.deck.populate();
        this.deck.shuffle();
    }

    public boolean hasBlackJack(List<Card> cards){
        return false;
    }

    public boolean has21(List<Card> cards){
        return false;
    }

    public void start(){
        this.currentPlayer = this.players.get(0);
        for (Player player : this.players){
            List<Card> cards = this.deck.takeCards(2);
            player.addCards(cards);
        }
    }

    public void runMove(){
        Move move = this.currentPlayer.getNextMove();
        if (move == Move.TWIST){
            this.currentPlayer.addCard(this.deck.takeCard());
        }

    }



}
