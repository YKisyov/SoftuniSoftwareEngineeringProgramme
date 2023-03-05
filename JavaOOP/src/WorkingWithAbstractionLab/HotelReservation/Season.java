package WorkingWithAbstractionLab.HotelReservation;

public enum Season {
    Autumn(1),
    Spring(2),
    Winter(3),
    Summer(4);

    private int seasonMultiplier;
    Season(int seasonMultiplier){
        this.seasonMultiplier = seasonMultiplier;
    }
    public int getSeasonMultiplier() {
        return seasonMultiplier;
    }
}
