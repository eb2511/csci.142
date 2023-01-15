package game;

import game.bet.BetType;
import game.cards.*;
import game.player.Player;

import java.util.ArrayList;

public class Dealer {   //only deals with betting
    private static String DISCARD_PILE_NAME = "discardPile";
    private Pile discardPile;
    private static int HIT_VALUE = 5;
    private static int MIN_CARDS_TO_RESHUFFLE=4;
    private static String SHOE_PILE_NAME = "shoePile";
    private Pile shoePile;
    private static int STAND_VALUE = 8;
    private Baccarat house;
    private Player[] players;


    public Dealer(Baccarat house, Player[] players){
        this.shoePile = new Pile(SHOE_PILE_NAME);
        this.discardPile = new Pile(DISCARD_PILE_NAME);
        this.house = house;
        for (Rank rank:Rank.values()) {
            for(Suit suit:Suit.values()){
                this.shoePile.addCard(new Card(rank,suit));
            }
        }
        this.shoePile.shuffle();
        this.players = players;
    }

    public void printHands(Hand p1, Hand p2){
        System.out.println("HANDS");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }

    public void checkAndShuffle(){
        if(this.shoePile.numCards()<MIN_CARDS_TO_RESHUFFLE){    //shuffle
            for (int i = 0; i<this.discardPile.numCards();i++){
                this.shoePile.addCard(this.discardPile.drawCard());
            }
            this.shoePile.shuffle();
        }
    }


    public BetType getWinner(){
        Hand player = new Hand("Player");
        Hand dealer = new Hand("Dealer");


            this.checkAndShuffle();
            player.addCard(this.shoePile.drawCard());//i wonder if there is a better way..
            player.addCard(this.shoePile.drawCard());
            dealer.addCard(this.shoePile.drawCard());
            dealer.addCard(this.shoePile.drawCard());
        this.printHands(player,dealer);

        //decide to hit or stand
        System.out.println("HIT/STAND");
        if(player.getValue()>=8||dealer.getValue()>=8){
            System.out.println("Player and Banker stand");
        }else{
            if (player.getValue()<=5){
                System.out.println("Player hits");
                this.checkAndShuffle();
                player.addCard(this.shoePile.drawCard());
                this.printHands(player,dealer);
            }
            if(dealer.getValue()<=5){
                System.out.println("Dealer hits");
                this.checkAndShuffle();
                dealer.addCard(this.shoePile.drawCard());
                this.printHands(player,dealer);
            }

        }

        discardPile.addCards((ArrayList<Card>) player.getCards());//recycle cards
        discardPile.addCards((ArrayList<Card>) dealer.getCards());

        //assign winner
        if(player.compareTo(dealer)>0){
            return BetType.PLAYER;
        }else if(player.compareTo(dealer)<0){
            return BetType.BANKER;
        }else{
            return BetType.TIE;
        }
    }


    public void playRound(){
        System.out.println("BETS");//make bets
        for(Player p:this.players){
            p.makeBet();
        }
        BetType winner = this.getWinner();//getwinner
        System.out.println("RESULTS");
        System.out.println("Winner: " + winner.toString());
        this.house.indicateWinner(winner);

        System.out.println("PLAYERS");//print out players.
        for(Player p:players){
            System.out.println(p.toString());
        }

    }


}
