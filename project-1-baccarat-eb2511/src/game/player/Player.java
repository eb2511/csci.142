package game.player;

import game.bet.Bet;
import game.bet.BetType;

//10.12 Mercury Bao
public abstract class Player {
    protected static int BET_AMOUNT = 1;
    protected String name;
    protected int id;
    protected double balance;
    protected int wins;
    protected int loses;
    protected double winPercentage;
    protected BetType type;

    public Player(String name, int id){
        this.name = name;
        this.id = id;
        this.wins  = 0;
        this.loses = 0;
        this.winPercentage = 0;
    }

    public double getPercentage(){
        Double d = this.wins*1.0;
        Double d2 =(this.wins+this.loses)*1.0;
        return this.winPercentage=d/d2;
    }

    public BetType getType(){return this.type;}

    public void addLoss(){
        this.loses+=1;
        System.out.println(this.name+" lost "+BET_AMOUNT);
        this.getPercentage();
    }

    public void addWin(){
        this.wins+=1;
        double payOut = this.type.getPayout(BET_AMOUNT);
        System.out.println(this.name+ " won "+payOut);
        this.adjustBalance(payOut);
        this.getPercentage();
    }


    public void adjustBalance(double amount){this.balance+=amount;}
    public double getAdjusted(){
        return this.type.getPayout(BET_AMOUNT);
    }
    public double getBalance(){return this.balance;}

    public int getWins(){return this.wins;}

    public abstract Bet makeBet();

    @Override
    public String toString(){
        String str = "Player{name='";
        str+= name;
        str+="', id=";
        str+=this.id;
        str+="', balance=";
        str+=this.balance;
        str+="', wins=";
        str+=this.wins;
        str+="', loses=";
        str+=this.loses;
        str+="', win percentage=";
        str+=this.getPercentage();
        return str+="}";
    }
}
