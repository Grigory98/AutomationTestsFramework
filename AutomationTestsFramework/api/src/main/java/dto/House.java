package dto;

public class House {
    private String floorCount;
    private double price;
    private ParkingPlace[] parkingPlaces;

    public String getFloorCount() {
        return floorCount;
    }

    public double getPrice() {
        return price;
    }

    public ParkingPlace[] getParkingPlaces() {
        return parkingPlaces;
    }
}
