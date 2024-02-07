package steps;

import base.Response;
import dto.Car;
import dto.User;
import dto.UserInfo;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import services.UserClientImpl;

public class UserSteps {
    UserClientImpl userClient = new UserClientImpl();

    @Step("Создание нового пользователя")
    public User createUser(
            final String firstName,
            final String secondName,
            final int age,
            final String sex,
            final double money
    ) {
        Response<User> response = userClient.createUser(firstName, secondName, age, sex, money);
        Assertions.assertEquals(201, response.getCode(), "Код должен быть 201");
        return response.getObject();
    }

    @Step("Получить список пользователей")
    public User[] getUsers() {
        Response<User[]> response = userClient.getUsers();
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }

    @Step("Получить пользователя c id = {userId}")
    public User getUser(final int userId) {
        Response<User> response = userClient.getUser(userId);
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }

    @Step("Получить полную информацию о пользователе {userId}")
    public UserInfo getUserInfo(final int userId) {
        Response<UserInfo> response = userClient.getUserInfo(userId);
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }

    @Step("Удалить пользователя с id = {userId}")
    public void deleteUser(final int userId) {
        userClient.deleteUser(userId);
    }

    @Step("Изменить данные пользователя с id = {userId}")
    public User updateUser(
            final int userId,
            final String firstName,
            final String secondName,
            final int age,
            final String sex,
            final double money
    ) {
        Response<User> response = userClient.updateUser(userId, firstName, secondName, age, sex, money);
        Assertions.assertEquals(202, response.getCode(), "Код должен быть 202");
        return response.getObject();
    }

    @Step("Добавить пользователю {userId} денег в количестве {amount}")
    public User addMoney(final int userId, final double amount) {
        Response<User> response = userClient.addMoney(userId, amount);
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }

    @Step("Купить пользователю {userId} машину")
    public User buyCar(final int userId, final Car car) {
        Response<User> response = userClient.buyCar(userId, car.getId());
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }

    @Step("Продать машину у пользователя {userId}")
    public User sellCar(final int userId, final Car car) {
        Response<User> response = userClient.sellCar(userId, car.getId());
        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");
        return response.getObject();
    }
}
