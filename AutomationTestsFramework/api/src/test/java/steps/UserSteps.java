package steps;

import dto.CarDTO;
import dto.UserDTO;
import dto.UserInfoDTO;
import io.qameta.allure.Step;
import services.UserClientImpl;

public class UserSteps {
    UserClientImpl userClient = new UserClientImpl();

    @Step("Создание нового пользователя")
    public UserDTO createUser(
            final String firstName,
            final String secondName,
            final int age,
            final String sex,
            final double money
    ) {
        UserDTO response = userClient.createUser(firstName, secondName, age, sex, money);
        return response;
    }

    @Step("Получить список пользователей")
    public UserDTO[] getUsers() {
        UserDTO[] response = userClient.getUsers();
        return response;
    }

    @Step("Получить пользователя c id = {userId}")
    public UserDTO getUser(final int userId) {
        UserDTO response = userClient.getUser(userId);
        return response;
    }

    @Step("Получить полную информацию о пользователе {userId}")
    public UserInfoDTO getUserInfo(final int userId) {
        UserInfoDTO response = userClient.getUserInfo(userId);
        return response;
    }

    @Step("Удалить пользователя с id = {userId}")
    public void deleteUser(final int userId) {
        userClient.deleteUser(userId);
    }

    @Step("Изменить данные пользователя с id = {userId}")
    public UserDTO updateUser(
            final int userId,
            final String firstName,
            final String secondName,
            final int age,
            final String sex,
            final double money
    ) {
        UserDTO response = userClient.updateUser(userId, firstName, secondName, age, sex, money);
        return response;
    }

    @Step("Добавить пользователю {userId} денег в количестве {amount}")
    public UserDTO addMoney(final int userId, final double amount) {
        UserDTO response = userClient.addMoney(userId, amount);
        return response;
    }

    @Step("Купить пользователю {userId} машину")
    public UserDTO buyCar(final int userId, final CarDTO carDTO) {
        UserDTO response = userClient.buyCar(userId, carDTO.getId());
        return response;
    }

    @Step("Продать машину у пользователя {userId}")
    public UserDTO sellCar(final int userId, final CarDTO carDTO) {
        UserDTO response = userClient.sellCar(userId, carDTO.getId());
        return response;
    }
}
