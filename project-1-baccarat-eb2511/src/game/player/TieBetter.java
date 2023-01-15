package game.player;

import game.bet.Bet;
import game.bet.BetType;

public class TieBetter extends Player{
    public TieBetter(String name, int id) {
        super(name, id);
        this.type = BetType.TIE;
    }

    @Override
    public Bet makeBet() {
        System.out.println(this.name+" bet "+BET_AMOUNT+" on TIE");
        this.adjustBalance(-BET_AMOUNT);
        return new Bet(this.id, BetType.TIE,BET_AMOUNT);
    }
}
