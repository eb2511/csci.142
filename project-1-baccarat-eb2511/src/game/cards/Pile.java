package game.cards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
//10.12 Mercury Bao
public class Pile {
    private static Random random = new Random();
    private String name;
    private ArrayList<Card> pile;

    public Pile(String name){
        this.pile=new ArrayList<>();
        this.name = name;
    }

    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    public int numCards() {
        return this.pile.size();
    }

    public void addCard(Card card) {
        this.pile.add(card);
    }

    public void addCards(ArrayList<Card> cards){
        this.pile.addAll(cards);
    }

    public Card drawCard() {
        return this.pile.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(this.pile,random);
    }

    @Override
    public String toString(){
        String str = this.name+" pile: ";
        for (Card c:this.pile) {
            str+= c.toString();
            str+= " ";
        }
        return str;
    }
}
