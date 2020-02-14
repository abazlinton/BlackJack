import java.util.List;
import java.util.Scanner;

public class Player implements IPlayer {

    private List<Card> cards;


    private Move nextMove;


    public void addCard(Card card){
        this.cards.add(card);
    }

    public void addCards(List<Card> cards){
        for (Card card : cards){
            this.addCard(card);
        }
    }

    public void setNextMove(Move move){
        this.nextMove = move;
    }

    public Move getNextMove() {
        return nextMove;
    }

    public BlackJackMove getMove(List<Card> cards) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's your move? (S)tick (T)wist");
        String moveChoice = scanner.next().toUpperCase().substring(0, 1);
        if (moveChoice.equals("S")){
            return BlackJackMove.STICK;
        } else if (moveChoice.equals("T")){
            return BlackJackMove.TWIST;
        }
        return null;
    }
}
