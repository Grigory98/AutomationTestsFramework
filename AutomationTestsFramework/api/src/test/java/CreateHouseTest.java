import base.Response;
import dto.House;
import dto.ParkingPlace;
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
}
