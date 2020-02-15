public class Card {

    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getCardDescription(){
        String oneLetterRank;
        if (rank.getIsFaceCard()){
            oneLetterRank = rank.toString().substring(0, 1);
        } else if (rank == Rank.ACE) {
            oneLetterRank = "A";
        } else if (rank == Rank.TEN) {
                oneLetterRank = "T";
        } else {
            oneLetterRank = String.valueOf(rank.ordinal() + 1);
        }
        return oneLetterRank + suit.toString().substring(0, 1).toLowerCase();
    }
}
