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

    public ParkingPlaceDTO(boolean isWarm, boolean isCovered, int placesCount) {
        this.isWarm = isWarm;
        this.isCovered = isCovered;
        this.placesCount = placesCount;
    }
}
