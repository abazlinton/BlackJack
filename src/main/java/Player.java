import enums.BlackJackMove;
import enums.WinState;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards;
    private Boolean hasStuck;
    private String name;
    private WinState winState;

    public Player(String name) {
        this.name = name;
        this.hasStuck = false;
        this.cards = new ArrayList<Card>();
        this.winState = null;
    }

    public WinState getWinState() {
        return winState;
    }

    public void setWinState(WinState winState) {
        this.winState = winState;
    }

    public String getName() {
        return name;
    }

    public Boolean getHasStuck() {
        return hasStuck;
    }

    public void setHasStuck(Boolean hasStuck) {
        this.hasStuck = hasStuck;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void addCards(List<Card> cards){
        for (Card card : cards){
            this.addCard(card);
        }
    }

}
