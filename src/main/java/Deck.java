import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private ArrayList<Card> cards;

    public void populate(){
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()) {
                this.cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card takeCard(){
        return this.cards.get(0);
    }

    public List<Card> takeCards(int number){
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < number; i++){
            cards.add(this.cards.remove(0));
        }
        return cards;
    }

}
