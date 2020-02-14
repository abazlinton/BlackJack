import java.util.List;

public class BlackJackScorer {

    public boolean haveScoredAnAceAs11;


    public BlackJackScorer() {
        this.haveScoredAnAceAs11 = false;
    }

    public int getHandScore(List<Card> cards){
        int handScore = 0;
        for (Card card : cards){
            handScore += getCardScore(card);
        }
        if (handScore > 21 && haveScoredAnAceAs11){
           handScore -= 10;
        }
        return handScore;
    }

    public int getCardScore(Card card){
        int cardScore;
        if (card.getRank() == Rank.ACE){
            if (!haveScoredAnAceAs11) {
                cardScore = 11;
                this.haveScoredAnAceAs11 = true;
            } else {
                cardScore = 1;
            }
        } else if (card.getRank().getIsFaceCard()) {
            cardScore = 10;
        } else {
            cardScore = card.getRank().ordinal() + 1;
        }
        return cardScore;
    }


}
