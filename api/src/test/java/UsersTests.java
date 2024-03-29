import dto.CarDTO;
import dto.UserDTO;
import dto.UserInfoDTO;
import generates.TestDataGenerate;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import services.CarsClient;
import services.CarsClientImpl;
import services.UserClient;
import services.UserClientImpl;

import java.util.Arrays;

@Epic("API")
@Feature("Тесты на взаимодействие пользователей")
public class UsersTests {
    private final UserClient userClient = new UserClientImpl();
    private final CarsClient carsClient = new CarsClientImpl();
    private UserDTO userDTO;

    @BeforeEach
    public void createUser() {
        String firstName = TestDataGenerate.generateString(10);
        String secondName = TestDataGenerate.generateString(10);
        int age = TestDataGenerate.generateInt(1, 100);
        int money = TestDataGenerate.generateInt(1, 10000);
        userDTO = userClient.createUser(firstName, secondName, age, "MALE", money);
    }

    @AfterEach
    public void deleteUser() {
        userClient.deleteUser(userDTO.getId());
    }

    @Owner("Grigory98")
    @DisplayName("Проверка создания пользователя")
    @Description("Тест на проверку создания нового пользователя")
    @Test
    public void createUserTest() {
        Assertions.assertEquals(
                userDTO.getId(),
                userClient.getUser(userDTO.getId()).getId(),
                "Пользователь найден в списке"
        );
    }

    @Owner("Grigory98")
    @DisplayName("Проверка изменения пользователя")
    @Description("Тест на проверку изменения данных пользователя")
    @Test
    public void updateUserTest() {
        UserDTO updatedUserDTO = updateUser();
        Assertions.assertEquals(userDTO.getId(), updatedUserDTO.getId(), "Id пользователей совпадает");
        Assertions.assertNotEquals(userDTO.getFirstName(), updatedUserDTO.getFirstName(), "Имя пользователей отличается");
        Assertions.assertNotEquals(userDTO.getSecondName(), updatedUserDTO.getSecondName(), "Фамилия пользователей отличается");
        Assertions.assertNotEquals(userDTO.getAge(), updatedUserDTO.getAge(), "Возраст пользователей отличается");
        Assertions.assertEquals(userDTO.getSex(), updatedUserDTO.getSex(), "Пол пользователей одинаковый");
        Assertions.assertNotEquals(userDTO.getMoney(), updatedUserDTO.getMoney(), "Сумма пользователей разное");
    }

    @Owner("Grigory98")
    @DisplayName("Добавление денег пользователю")
    @Description("Тест на добавление случайно сгенерированной суммы пользователю")
    @Test
    public void addMoneyTest() {
        int money = TestDataGenerate.generateInt(1, 10000);
        double expectedUserAmount = userDTO.getMoney() + money;
        userClient.addMoney(userDTO.getId(), money);
        userDTO = userClient.getUser(userDTO.getId());
        Assertions.assertEquals(userDTO.getMoney(), expectedUserAmount, "Сумма у пользователя совпадает с ожидаемым");
    }

    @Owner("Grigory98")
    @DisplayName("Покупка нескольких машин пользователем")
    @Description("Тест на покупку нескольких машин пользователю")
    @Test
    public void buyCars() {
        int price = 100000;
        userClient.addMoney(userDTO.getId(), price * 2);
        CarDTO labma = carsClient.createCar("Electric", "Lamba", "Gallardo", price);
        CarDTO ferra = carsClient.createCar("Gasoline", "Ferra", "California", price);
        try {
            userClient.buyCar(userDTO.getId(), labma.getId());
            userClient.buyCar(userDTO.getId(), ferra.getId());
            UserInfoDTO userInfoDTO = userClient.getUserInfo(userDTO.getId());

            Assertions.assertEquals(userInfoDTO.getCars().length, 2, "У пользователя имеется 2 машины");
            Assertions.assertNotNull(Arrays.stream(userInfoDTO.getCars()).filter(c -> c.getId() == labma.getId()).findAny(), "Куплена Lamba");
            Assertions.assertNotNull(Arrays.stream(userInfoDTO.getCars()).filter(c -> c.getId() == ferra.getId()).findAny(), "Куплена Ferra");
            Assertions.assertEquals(userDTO.getMoney(), userInfoDTO.getMoney(), "Сумма не изменилась");
        } finally {
            userClient.sellCar(userDTO.getId(), labma.getId());
            userClient.sellCar(userDTO.getId(), ferra.getId());
            carsClient.deleteCar(labma.getId());
            carsClient.deleteCar(ferra.getId());
        }
    }

    private UserDTO updateUser() {
        String firstName = TestDataGenerate.generateString(10);
        String secondName = TestDataGenerate.generateString(10);
        int age = TestDataGenerate.generateInt(1, 100);
        int money = TestDataGenerate.generateInt(1, 10000);
        return userClient.updateUser(userDTO.getId(), firstName, secondName, age,"MALE", money);
    }
}
