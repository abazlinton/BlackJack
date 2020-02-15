import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BlackJackScorerTest {



    @Test
    public void test_one_ace_as_11(){
        Card ace1 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ace2 = new Card(Rank.ACE, Suit.DIAMONDS);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(ace1);
        cards.add(ace2);
        int score = BlackJackScorer.getHandScore(cards);
        assertEquals(12, score);
    }


    @Test
    public void test_all_aces_score_one(){
        Card ace1 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ace2 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ace3 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card ten = new Card(Rank.TEN, Suit.DIAMONDS);
        Card eight = new Card(Rank.EIGHT, Suit.DIAMONDS);
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(ace1);
        cards.add(ace2);
        cards.add(ace3);
        cards.add(ten);
        cards.add(eight);
        int score = BlackJackScorer.getHandScore(cards);
        assertEquals(21, score);
    }


}
