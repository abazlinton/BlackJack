import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BlackJackScorerTest {



    @Test
    public void test_one_ace_scores_11(){
        Card ace1 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ace2 = new Card(Rank.ACE, Suit.DIAMONDS);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(ace1);
        cards.add(ace2);
        int score = BlackJackScorer.getHandScore(cards);
        assertEquals(12, score);
    }


    @Test
    public void test_all_aces_score_1(){
        Card ace1 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ace2 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ten = new Card(Rank.TEN, Suit.DIAMONDS);
        Card nine = new Card(Rank.NINE, Suit.DIAMONDS);
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(ace1);
        cards.add(ace2);
        cards.add(ten);
        cards.add(nine);
        int score = BlackJackScorer.getHandScore(cards);
        assertEquals(21, score);
    }


}
