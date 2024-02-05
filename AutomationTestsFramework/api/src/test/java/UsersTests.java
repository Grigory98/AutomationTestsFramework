import dto.User;
import org.junit.jupiter.api.*;
import steps.UserSteps;

public class UsersTests {
    private final UserSteps userSteps = new UserSteps();
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
        Assertions.assertNotEquals(user.getMoney(), updatedUser.getMoney(), "Количество денег пользователей разное");
    }

    @Test
    @DisplayName("Добавление денег пользователю")
    public void addMoneyTest() {
        final int money = TestDataGenerate.generateInt(1, 10000);
        final double expectedUserAmount = user.getMoney() + money;
        userSteps.addMoney(user.getId(), money);
        user = userSteps.getUser(user.getId());
        Assertions.assertEquals(user.getMoney(), expectedUserAmount, "Количество денег у пользователя совпадает с ожидаемым");
    }

    private User updateUser() {
        final String firstName = TestDataGenerate.generateString(10);
        final String secondName = TestDataGenerate.generateString(10);
        final int age = TestDataGenerate.generateInt(1, 100);
        final int money = TestDataGenerate.generateInt(1, 10000);
        return userSteps.updateUser(user.getId(), firstName, secondName, age,"MALE", money);
    }
}
