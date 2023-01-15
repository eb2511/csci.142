package game.player;

import game.Baccarat;
import game.bet.Bet;
import game.bet.BetType;

public class BankBetter extends Player{

    public BankBetter(String name, int id) {
        super(name, id);
        this.type = BetType.BANKER;

    }

    @Override
    public Bet makeBet() {
        System.out.println(this.name+" bet "+BET_AMOUNT+" on BANKER");
        this.adjustBalance(-BET_AMOUNT);
        return new Bet(this.id, BetType.BANKER,BET_AMOUNT);
    }
}
