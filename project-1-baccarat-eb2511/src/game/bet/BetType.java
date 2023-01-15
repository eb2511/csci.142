package game.bet;
//10.12 Mercury Bao
public enum BetType {
    PLAYER (),
    BANKER (),
    TIE ();


    public double getPayout(double amount) {
        if (this == BetType.PLAYER){
            return amount*2;
        }else if(this == BetType.BANKER){
            return amount*1.95;
        }else {
            return amount*9;
        }
    }}

