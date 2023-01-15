package game.player;

import game.bet.Bet;
import game.bet.BetType;

public class PlayerBetter extends Player{
    public PlayerBetter(String name, int id) {
        super(name, id);
        this.type = BetType.PLAYER;
    }

    @Override
    public Bet makeBet() {
        System.out.println(this.name+" bet "+BET_AMOUNT+" on PLAYER");
        this.adjustBalance(-BET_AMOUNT);
        return new Bet(this.id, BetType.PLAYER,BET_AMOUNT);
    }
}
