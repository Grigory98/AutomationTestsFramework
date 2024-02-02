package dto;

public class UserInfo extends User {
    private int house;
    private Car[] cars;

    public int getHouse() {
        return house;
    }

    public Car[] getCars() {
        return cars;
    }
}
