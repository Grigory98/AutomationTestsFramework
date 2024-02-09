import dto.CarDTO;
import dto.UserDTO;
import dto.UserInfoDTO;
import org.junit.jupiter.api.*;
import steps.CarSteps;
import steps.UserSteps;

import java.util.Arrays;

public class UsersTests {
    private final UserSteps userSteps = new UserSteps();
    private final CarSteps carSteps = new CarSteps();
    private UserDTO userDTO;

    @BeforeEach
    public void createUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        userDTO = userSteps.createUser(firstName, secondName, age, "MALE", money);
    }

    @AfterEach
    public void deleteUser() {
        userSteps.deleteUser(userDTO.getId());
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUserTest() {
        Assertions.assertEquals(
                userDTO.getId(),
                userSteps.getUser(userDTO.getId()).getId(),
                "Пользователь найден в списке"
        );
    }

    @Test
    @DisplayName("Проверка изменения пользователя")
    public void updateUserTest() {
        final UserDTO updatedUserDTO = updateUser();
        Assertions.assertEquals(userDTO.getId(), updatedUserDTO.getId(), "Id пользователей совпадает");
        Assertions.assertNotEquals(userDTO.getFirstName(), updatedUserDTO.getFirstName(), "Имя пользователей отличается");
        Assertions.assertNotEquals(userDTO.getSecondName(), updatedUserDTO.getSecondName(), "Фамилия пользователей отличается");
        Assertions.assertNotEquals(userDTO.getAge(), updatedUserDTO.getAge(), "Возраст пользователей отличается");
        Assertions.assertEquals(userDTO.getSex(), updatedUserDTO.getSex(), "Пол пользователей одинаковый");
        Assertions.assertNotEquals(userDTO.getMoney(), updatedUserDTO.getMoney(), "Сумма пользователей разное");
    }

    @Test
    @DisplayName("Добавление денег пользователю")
    public void addMoneyTest() {
        final int money = TestDataGenerate.generateInt(1, 10000);
        final double expectedUserAmount = userDTO.getMoney() + money;
        userSteps.addMoney(userDTO.getId(), money);
        userDTO = userSteps.getUser(userDTO.getId());
        Assertions.assertEquals(userDTO.getMoney(), expectedUserAmount, "Сумма у пользователя совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Покупка нескольких машин пользователем")
    public void buyCars() {
        int price = 100000;
        userSteps.addMoney(userDTO.getId(), price * 2);
        final CarDTO labma = carSteps.createCar("Electric", "Lamba", "Gallardo", price);
        final CarDTO ferra = carSteps.createCar("Gasoline", "Ferra", "California", price);
        try {
            userSteps.buyCar(userDTO.getId(), labma);
            userSteps.buyCar(userDTO.getId(), ferra);
            UserInfoDTO userInfoDTO = userSteps.getUserInfo(userDTO.getId());

            Assertions.assertEquals(userInfoDTO.getCars().length, 2, "У пользователя имеется 2 машины");
            Assertions.assertNotNull(Arrays.stream(userInfoDTO.getCars()).filter(c -> c.getId() == labma.getId()).findAny(), "Куплена Lamba");
            Assertions.assertNotNull(Arrays.stream(userInfoDTO.getCars()).filter(c -> c.getId() == ferra.getId()).findAny(), "Куплена Ferra");
            Assertions.assertEquals(userDTO.getMoney(), userInfoDTO.getMoney(), "Сумма не изменилась");
        } finally {
            userSteps.sellCar(userDTO.getId(), labma);
            userSteps.sellCar(userDTO.getId(), ferra);
            carSteps.deleteCar(labma.getId());
            carSteps.deleteCar(ferra.getId());
        }
    }

    private UserDTO updateUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        return userSteps.updateUser(userDTO.getId(), firstName, secondName, age,"MALE", money);
    }
}
