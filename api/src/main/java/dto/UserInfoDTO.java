package dto;

public class UserInfoDTO extends UserDTO {
    private int house;
    private CarDTO[] cars;

    public int getHouse() {
        return house;
    }

    public CarDTO[] getCars() {
        return cars;
    }
}
