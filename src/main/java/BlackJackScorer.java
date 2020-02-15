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
        boolean haveScoredFirstAceAs11 = false;
        for (Card card : cards){
            int cardScore = cardScores.get(card.getRank());
            if (cardScore == 11) {
                cardScores.put(Rank.ACE, 1);
                haveScoredFirstAceAs11 = true;
            }
            handScore += cardScore;
        }
        if (handScore > 21 && haveScoredFirstAceAs11){
           handScore -= 10;
        }
        return handScore;
    }


}
