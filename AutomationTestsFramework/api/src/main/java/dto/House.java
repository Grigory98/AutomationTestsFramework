package dto;

public class House {
    private int id;
    private String floorCount;
    private double price;
    private ParkingPlace[] parkingPlaces;

    private User[] lodgers;

    public int getId() {
        return id;
    }
    public String getFloorCount() {
        return floorCount;
    }

    public double getPrice() {
        return price;
    }

    public ParkingPlace[] getParkingPlaces() {
        return parkingPlaces;
    }

    public User[] getLodgers() {
        return lodgers;
    }

}
