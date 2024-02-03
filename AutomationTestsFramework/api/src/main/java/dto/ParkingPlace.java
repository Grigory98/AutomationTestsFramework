package dto;

public class ParkingPlace {
    private boolean isWarm;
    private boolean isCovered;
    private int placesCount;

    public boolean isWarm() {
        return isWarm;
    }

    public boolean isCovered() {
        return isCovered;
    }

    public int getPlacesCount() {
        return placesCount;
    }

    public ParkingPlace(final boolean isWarm, final boolean isCovered, final int placesCount) {
        this.isWarm = isWarm;
        this.isCovered = isCovered;
        this.placesCount = placesCount;
    }
}
