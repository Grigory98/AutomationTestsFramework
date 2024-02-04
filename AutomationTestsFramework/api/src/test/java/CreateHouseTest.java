import base.Response;
import dto.House;
import dto.ParkingPlace;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateHouseTest {
    @Test
    public void createHouse() {
        HouseClientImpl houseClient = new HouseClientImpl();


        ParkingPlace parkingPlace1 = houseClient.createParkingPlace(false, false, 228);
        ParkingPlace parkingPlace2 = houseClient.createParkingPlace(true, true, 10000);
        Response<House> response = houseClient.createHouse(1, 1000, new ParkingPlace[] {parkingPlace1, parkingPlace2});

        Assertions.assertEquals(201, response.getCode(), "Код должен быть 201");
    }

    @Test
    public void moveLodgerToHouseTest() {
        HouseClientImpl houseClient = new HouseClientImpl();
        UserClientImpl userClient = new UserClientImpl();

        User user = userClient.getUser(6867).getObject();
        House house = houseClient.getHouse(50).getObject();

        Response<House> response = houseClient.addLodger(house.getId(), user.getId());

        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
    }

    @Test
    public void removeLodgerFromHouseTest() {
        HouseClientImpl houseClient = new HouseClientImpl();
        UserClientImpl userClient = new UserClientImpl();

        User user = userClient.getUser(6862).getObject();
        House house = houseClient.getHouse(50).getObject();

        Response<House> response = houseClient.removeLodger(house.getId(), user.getId());

        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
    }
}
