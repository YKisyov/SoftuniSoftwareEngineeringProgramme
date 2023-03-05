package WorkingWithAbstractionExercise.CardsWithPower;

public enum CardPowerSuit {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private int suitePower;
    CardPowerSuit(int suitePower){
        this.suitePower = suitePower;
    }
    public int getSuitePower() {
        return suitePower;
    }
}
