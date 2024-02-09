package dto;

public class ParkingPlaceDTO {
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

    public ParkingPlaceDTO(final boolean isWarm, final boolean isCovered, final int placesCount) {
        this.isWarm = isWarm;
        this.isCovered = isCovered;
        this.placesCount = placesCount;
    }
}
