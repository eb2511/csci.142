package game;

import game.bet.BetType;
import game.cards.Pile;
import game.player.*;

public class Baccarat {
    private static  String BANKER_BETTER = "BankerBetter";
    private static  String CUSTOM_BETTER = "CustomBetter";
    private static int NUM_PLAYERS = 4;
    private static String PLAYER_BETTER = "PlayerBetter";
    private static String TIE_BETTER = "TieBetter";
    private int rounds;
    private BankBetter bankBetter= new BankBetter(BANKER_BETTER,1);
    private CustomBetter customBetter= new CustomBetter(this,CUSTOM_BETTER,3);
    private PlayerBetter playerBetter= new PlayerBetter(PLAYER_BETTER,0);
    private TieBetter tieBetter = new TieBetter(TIE_BETTER,2);
    private double houseBalance;

    public Baccarat(int rounds){
        this.rounds = rounds;
        this.houseBalance = 0;

    }

    /*there is probably a way better way to do this i don't understand
    / why i have to do these
    */
    public void earn(double amount){this.houseBalance+=amount;}
    public int getBankerHandWins(){return this.bankBetter.getWins();}
    public double getBankerWinPct(){return this.bankBetter.getPercentage();}
    public int getPlayerHandWins(){return this.playerBetter.getWins();}
    public double getPlayerWinPct(){return this.playerBetter.getPercentage();}
    public int getTieHandWins(){return this.tieBetter.getWins();}
    public double getTieWinPct(){return this.tieBetter.getPercentage();}
    //

    public void indicateWinner(BetType winner){
        this.earn(4);
        if (this.customBetter.getType().equals(winner)){//deal with custom first
            this.customBetter.addWin();
            this.earn(-this.customBetter.getAdjusted());
        }else {
            this.customBetter.addLoss();
        }
        if(winner==BetType.BANKER){
            this.bankBetter.addWin();
            this.earn(-this.bankBetter.getAdjusted());
            this.tieBetter.addLoss();
            this.playerBetter.addLoss();
        }else if(winner==BetType.PLAYER){
            this.bankBetter.addLoss();
            this.tieBetter.addLoss();
            this.playerBetter.addWin();
            this.earn(-this.playerBetter.getAdjusted());
        }else {
            this.bankBetter.addLoss();
            this.tieBetter.addWin();
            this.earn(-this.tieBetter.getAdjusted());
            this.playerBetter.addLoss();
        }
    }

    public Player[] getPlayers(){
        Player[] players = new Player[4];
        players[0] = this.playerBetter;
        players[1] = this.bankBetter;
        players[2] = this.tieBetter;
        players[3] = this.customBetter;
        return players;
    }



    public void playGame(){
        Dealer dealer = new Dealer(this,this.getPlayers());
        for(int i = 0;i<this.rounds;i++){
            System.out.println("ROUND "+(i+1));     //play one round
            dealer.playRound();
        }
        System.out.println("STATISTICS");           //print stats
        System.out.println("Round played: "+this.rounds);
        System.out.println("Player hand wins: "+this.getPlayerHandWins());
        System.out.println("Banker hand wins: "+this.getBankerHandWins());
        System.out.println("Tie hand wins: "+this.getTieHandWins());
        System.out.println("Player hand win %: "+this.getPlayerWinPct());
        System.out.println("Banker hand win %: "+this.getBankerWinPct());
        System.out.println("Tie hand win %: "+this.getTieWinPct());
        System.out.println("House balance: "+this.houseBalance);
    }



    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("Usage: java Baccarat seed round");
        }else{
            int seed = Integer.parseInt(args[0]);
            int round = Integer.parseInt(args[1]);
            Baccarat house = new Baccarat(round);
            System.out.println(house.playerBetter.toString());
            Pile.setSeed(seed);
            house.playGame();
        }
    }
}