package dto;

public class HouseDTO {
    private int id;
    private String floorCount;
    private double price;
    private ParkingPlaceDTO[] parkingPlaceDTOS;

    private UserDTO[] lodgers;

    public int getId() {
        return id;
    }
    public String getFloorCount() {
        return floorCount;
    }

    public double getPrice() {
        return price;
    }

    public ParkingPlaceDTO[] getParkingPlaces() {
        return parkingPlaceDTOS;
    }

    public UserDTO[] getLodgers() {
        return lodgers;
    }

}
