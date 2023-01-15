package game.player;

import game.Baccarat;
import game.bet.Bet;
import game.bet.BetType;


public class CustomBetter extends Player{
    private Baccarat house;
    private static double BANKER_WIN_PCT ;
    private static double PLAYER_WIN_PCT ;
    private static double TIE_WIN_PCT ;


    public CustomBetter(Baccarat house, String name, int id) {
        super(name, id);
        this.house = house;
         BANKER_WIN_PCT=0;
         PLAYER_WIN_PCT = 0;
         TIE_WIN_PCT = 0;

    }

    private void renew(){
        BANKER_WIN_PCT=this.house.getBankerWinPct();
        PLAYER_WIN_PCT = this.house.getPlayerWinPct();
        TIE_WIN_PCT = this.house.getTieWinPct();
    }

    @Override
    public Bet makeBet() {
        this.renew();
        this.adjustBalance(-BET_AMOUNT);
        if(Double.isNaN(BANKER_WIN_PCT)){
            System.out.println(this.name+" bet "+BET_AMOUNT+" on BANKER");
            this.type = BetType.BANKER;
            return new Bet(this.id, BetType.BANKER,BET_AMOUNT);
        }
        else if(BANKER_WIN_PCT > PLAYER_WIN_PCT){
            if(BANKER_WIN_PCT > TIE_WIN_PCT){
                System.out.println(this.name+" bet "+BET_AMOUNT+" on BANKER");
                this.type = BetType.BANKER;
                return new Bet(this.id, BetType.BANKER,BET_AMOUNT);
            }else{
                System.out.println(this.name+" bet "+BET_AMOUNT+" on TIE");
                this.type = BetType.TIE;
                return new Bet(this.id, BetType.TIE,BET_AMOUNT);
            }
        }else if(PLAYER_WIN_PCT>TIE_WIN_PCT){
            System.out.println(this.name+" bet "+BET_AMOUNT+" on PLAYER");
            this.type = BetType.PLAYER;
            return new Bet(this.id, BetType.PLAYER,BET_AMOUNT);
        }else{
            System.out.println(this.name+" bet "+BET_AMOUNT+" on TIE");
            this.type = BetType.TIE;
            return new Bet(this.id, BetType.TIE,BET_AMOUNT);
        }
    }
}
