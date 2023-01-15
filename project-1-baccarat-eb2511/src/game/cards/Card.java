package game.cards;
//10.12 Mercury Bao
public class Card {
    private Rank rank;
    private Suit suit;
    private int value;

    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
}

@Override
    public boolean equals(Object o){
    if(! (o instanceof Card)){
        return false;
    }else return this.rank.getValue() == ((Card) o).rank.getValue();
    }

@Override
    public String toString(){
        return this.rank.toString()+this.suit.toString();

}


    public int getValue() {
        return this.rank.getValue();
    }
}




