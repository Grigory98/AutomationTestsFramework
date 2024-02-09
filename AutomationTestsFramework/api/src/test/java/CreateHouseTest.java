import base.Response;
import dto.HouseDTO;
import dto.ParkingPlaceDTO;
import dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.HouseClientImpl;
import services.UserClientImpl;

public class CreateHouseTest {
    @Test
    public void createHouse() {
        HouseClientImpl houseClient = new HouseClientImpl();

        ParkingPlaceDTO parkingPlaceDTO1 = houseClient.createParkingPlace(false, false, 228);
        ParkingPlaceDTO parkingPlaceDTO2 = houseClient.createParkingPlace(true, true, 10000);
        HouseDTO response = houseClient.createHouse(1, 1000, new ParkingPlaceDTO[] {parkingPlaceDTO1, parkingPlaceDTO2});
    }

    @Test
    public void moveLodgerToHouseTest() {
        HouseClientImpl houseClient = new HouseClientImpl();
        UserClientImpl userClient = new UserClientImpl();

        UserDTO userDTO = userClient.getUser(6867);
        HouseDTO houseDTO = houseClient.getHouse(50);

        HouseDTO response = houseClient.addLodger(houseDTO.getId(), userDTO.getId());
    }

    @Test
    public void removeLodgerFromHouseTest() {
        HouseClientImpl houseClient = new HouseClientImpl();
        UserClientImpl userClient = new UserClientImpl();

        UserDTO userDTO = userClient.getUser(6862);
        HouseDTO houseDTO = houseClient.getHouse(50);

        HouseDTO response = houseClient.removeLodger(houseDTO.getId(), userDTO.getId());
    }
}
