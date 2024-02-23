import dto.HouseDTO;
import dto.ParkingPlaceDTO;
import dto.UserDTO;
import generates.TestDataGenerate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HouseClientImpl;
import services.UserClientImpl;

public class CreateHouseTest {
    private final UserClientImpl userSteps = new UserClientImpl();
    private final HouseClientImpl houseClient = new HouseClientImpl();
    private UserDTO userDTO;
    private HouseDTO house;

    @BeforeEach
    public void createUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        userDTO = userSteps.createUser(firstName, secondName, age, "MALE", money);
    }

    @BeforeEach
    public void createHouse() {
        ParkingPlaceDTO parkingPlaceFirst = houseClient.createParkingPlace(false, false, 228);
        ParkingPlaceDTO parkingPlaceSecond = houseClient.createParkingPlace(true, true, 10000);
        house = houseClient.createHouse(1, 1000, new ParkingPlaceDTO[] { parkingPlaceFirst, parkingPlaceSecond });
    }

    @AfterEach
    public void deleteUser() {
        userSteps.deleteUser(userDTO.getId());
        houseClient.deleteHouse(house.getId());
    }

    @Test
    public void createHouseTest() {
        Assertions.assertEquals(
                house.getId(),
                houseClient.getHouse(house.getId()).getId()
        );
    }

    @Test
    public void moveLodgerToHouseTest() {
        final HouseDTO updHouse = houseClient.addLodger(house.getId(), userDTO.getId());
        Assertions.assertEquals(updHouse.getLodgers().length, 1, "Количество жильцов равно 1");
    }

    @Test
    public void removeLodgerFromHouseTest() {
        houseClient.addLodger(house.getId(), userDTO.getId());
        final HouseDTO updHouse = houseClient.removeLodger(house.getId(), userDTO.getId());
        Assertions.assertEquals(updHouse.getLodgers().length, 0, "Количество жильцов равно 0");
    }
}
