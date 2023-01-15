package game.cards;

import java.util.ArrayList;
import java.util.Collection;
//10.12 Mercury Bao
public class Hand {
    private String name;
    private ArrayList<Card> hand;

    public Hand(String name){
        this.hand = new ArrayList<>();
        this.name = name;
    }

    public int getValue() {
        int sum = 0;
        for(Card card : this.hand){
            sum += card.getValue();
        }
        while(sum >= 10){
            sum -= 10;
        }
        return sum;
    }

    public void addCard(Card card) {
        this.hand.add(card);
    }

    public int compareTo(Hand hand2) {
        return  this.getValue()-hand2.getValue();
    }

    public Collection<Card> getCards() {
        return this.hand;
    }

    public void clear() {
        this.hand.clear();
    }

    @Override
    public String toString(){
        String str = this.name+" ("+this.getValue()+"): ";
        for (Card c:this.hand) {
            str+= c.toString();
            str+= " ";
        }
        return str;
    }
}
