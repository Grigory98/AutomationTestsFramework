import dto.HouseDTO;
import dto.ParkingPlaceDTO;
import dto.UserDTO;
import generates.TestDataGenerate;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import services.HouseClientImpl;
import services.UserClientImpl;

@Epic("API")
@Feature("Тесты на взаимодействие с домами")
public class CreateHouseTest {
    private final UserClientImpl userSteps = new UserClientImpl();
    private final HouseClientImpl houseClient = new HouseClientImpl();
    private UserDTO userDTO;
    private HouseDTO house;

    @BeforeEach
    public void createUser() {
        String firstName = TestDataGenerate.generateString(10);
        String secondName = TestDataGenerate.generateString(10);
        int age = TestDataGenerate.generateInt(1, 100);
        int money = TestDataGenerate.generateInt(1, 10000);
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

    @Owner("Grigory98")
    @DisplayName("Создание нового дома")
    @Description("Тест на создание нового дома")
    @Test
    public void createHouseTest() {
        Assertions.assertEquals(
                house.getId(),
                houseClient.getHouse(house.getId()).getId()
        );
    }

    @Owner("Grigory98")
    @DisplayName("Заселение жильца в дом")
    @Description("Тест на заселение жильца в дом")
    @Test
    public void moveLodgerToHouseTest() {
        HouseDTO updHouse = houseClient.addLodger(house.getId(), userDTO.getId());
        Assertions.assertEquals(updHouse.getLodgers().length, 1, "Количество жильцов равно 1");
    }

    @Owner("Grigory98")
    @DisplayName("Выселение жильца из дома")
    @Description("Тест на выселение жильца из дома")
    @Test
    public void removeLodgerFromHouseTest() {
        houseClient.addLodger(house.getId(), userDTO.getId());
        HouseDTO updHouse = houseClient.removeLodger(house.getId(), userDTO.getId());
        Assertions.assertEquals(updHouse.getLodgers().length, 0, "Количество жильцов равно 0");
    }
}
