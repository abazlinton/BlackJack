import java.util.List;

public class Player {

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


}
