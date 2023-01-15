package game.bet;
//10.12 Mercury Bao
public class Bet {
    private int playerID;
    private BetType type;
    private int amount;

    public Bet(int playerID, BetType type, int amount) {
        this.amount = amount;
        this.type = type;
        this.playerID = playerID;
    }

    public BetType getType() {
        return this.type;
    }

    public int getAmount() {
        return this.amount;
    }

    public double getPayout() {
        return this.type.getPayout(amount);
    }

    @Override
    public String toString(){
        return this.playerID+" "+this.type.toString()+" "+this.amount;
    }
}
