import java.util.HashMap;
import java.util.List;

public class BlackJackScorer {

    private static HashMap<Rank, Integer> cardScores;

    private static void buildInitialCardScores(){
        cardScores = new HashMap<>();
        for (Rank rank : Rank.values()){
            if (rank == Rank.ACE){
                cardScores.put(rank, 11);
            } else if (rank.getIsFaceCard()){
                cardScores.put(rank, 10);
            } else {
                cardScores.put(rank, rank.ordinal() + 1);
            }
        }
    }

    public static int getHandScore(List<Card> cards){
        buildInitialCardScores();
        int handScore = 0;
        for (Card card : cards){
            int cardScore = cardScores.get(card.getRank());
            if (card.getRank() == Rank.ACE){
                cardScores.put(Rank.ACE, 1);
            }
            handScore += cardScore;
        }
        boolean haveScoredAnAceAs11 = cardScores.get(Rank.ACE) == 1;
        if (handScore > 21 && haveScoredAnAceAs11){
           handScore -= 10;
        }
        return handScore;
    }


}
