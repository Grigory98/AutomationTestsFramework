import dto.Car;
import dto.User;
import dto.UserInfo;
import org.junit.jupiter.api.*;
import steps.CarSteps;
import steps.UserSteps;

import java.util.Arrays;

public class UsersTests {
    private final UserSteps userSteps = new UserSteps();
    private final CarSteps carSteps = new CarSteps();
    private User user;

    @BeforeEach
    public void createUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        user = userSteps.createUser(firstName, secondName, age, "MALE", money);
    }

    @AfterEach
    public void deleteUser() {
        userSteps.deleteUser(user.getId());
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    public void createUserTest() {
        Assertions.assertEquals(
                user.getId(),
                userSteps.getUser(user.getId()).getId(),
                "Пользователь найден в списке"
        );
    }

    @Test
    @DisplayName("Проверка изменения пользователя")
    public void updateUserTest() {
        final User updatedUser = updateUser();
        Assertions.assertEquals(user.getId(), updatedUser.getId(), "Id пользователей совпадает");
        Assertions.assertNotEquals(user.getFirstName(), updatedUser.getFirstName(), "Имя пользователей отличается");
        Assertions.assertNotEquals(user.getSecondName(), updatedUser.getSecondName(), "Фамилия пользователей отличается");
        Assertions.assertNotEquals(user.getAge(), updatedUser.getAge(), "Возраст пользователей отличается");
        Assertions.assertEquals(user.getSex(), updatedUser.getSex(), "Пол пользователей одинаковый");
        Assertions.assertNotEquals(user.getMoney(), updatedUser.getMoney(), "Сумма пользователей разное");
    }

    @Test
    @DisplayName("Добавление денег пользователю")
    public void addMoneyTest() {
        final int money = TestDataGenerate.generateInt(1, 10000);
        final double expectedUserAmount = user.getMoney() + money;
        userSteps.addMoney(user.getId(), money);
        user = userSteps.getUser(user.getId());
        Assertions.assertEquals(user.getMoney(), expectedUserAmount, "Сумма у пользователя совпадает с ожидаемым");
    }

    @Test
    @DisplayName("Покупка нескольких машин пользователем")
    public void buyCars() {
        int price = 100000;
        userSteps.addMoney(user.getId(), price * 2);
        final Car labma = carSteps.createCar("Electric", "Lamba", "Gallardo", price);
        final Car ferra = carSteps.createCar("Gasoline", "Ferra", "California", price);
        try {
            userSteps.buyCar(user.getId(), labma);
            userSteps.buyCar(user.getId(), ferra);
            UserInfo userInfo = userSteps.getUserInfo(user.getId());

            Assertions.assertEquals(userInfo.getCars().length, 2, "У пользователя имеется 2 машины");
            Assertions.assertNotNull(Arrays.stream(userInfo.getCars()).filter(c -> c.getId() == labma.getId()).findAny(), "Куплена Lamba");
            Assertions.assertNotNull(Arrays.stream(userInfo.getCars()).filter(c -> c.getId() == ferra.getId()).findAny(), "Куплена Ferra");
            Assertions.assertEquals(user.getMoney(), userInfo.getMoney(), "Сумма не изменилась");
        } finally {
            userSteps.sellCar(user.getId(), labma);
            userSteps.sellCar(user.getId(), ferra);
            carSteps.deleteCar(labma.getId());
            carSteps.deleteCar(ferra.getId());
        }
    }

    private User updateUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        return userSteps.updateUser(user.getId(), firstName, secondName, age,"MALE", money);
    }
}
