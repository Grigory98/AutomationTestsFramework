package services;

import base.Request;
import dto.UserDTO;
import dto.UserInfoDTO;
import io.qameta.allure.Step;

import java.util.LinkedHashMap;

public class UserClientImpl implements UserClient {

    private final String token = Token.authorization();

    @Override
    @Step("Получение всех пользователей")
    public UserDTO[] getUsers() {
        return new Request<UserDTO[]>().get("/users", UserDTO[].class);
    }

    @Override
    @Step("Получение пользователя с id = {id}")
    public UserDTO getUser(int id) {
        return new Request<UserDTO>().get("/user/" + id, UserDTO.class);
    }

    @Override
    @Step("Получение информации о пользователе {id}")
    public UserInfoDTO getUserInfo(int id) {
        return new Request<UserInfoDTO>().get("/user/" + id + "/info", UserInfoDTO.class);
    }

    @Step("Добавление нового пользователя")
    public UserDTO createUser(
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("firstName", firstName);
        params.put("secondName", secondName);
        params.put("age", age);
        params.put("sex", sex);
        params.put("money", money);

        return new Request<UserDTO>().post(token, "/user", params, UserDTO.class);
    }

    @Step("Обновление данных пользователя {id}")
    public UserDTO updateUser(
            final int id,
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("firstName", firstName);
        params.put("secondName", secondName);
        params.put("age", age);
        params.put("sex", sex);
        params.put("money", money);

        return new Request<UserDTO>().put(token, "/user/" + id, params, UserDTO.class);
    }

    @Step("Добавление суммы в количестве {amount} пользователю {userId}")
    public UserDTO addMoney(int userId, double amount) {
        String url = "/user/" + userId + "/money/" + amount;
        return new Request<UserDTO>().post(token, url, null, UserDTO.class);
    }

    @Step("Купить пользователю {userId} машину {carId}")
    public UserDTO buyCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/buyCar/" + carId;
        return new Request<UserDTO>().post(token, url, null, UserDTO.class);
    }

    @Step("Продать машину {carId} у пользователя {userId}")
    public UserDTO sellCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/sellCar/" + carId;
        return new Request<UserDTO>().post(token, url, null, UserDTO.class);
    }

    @Step("Удаление пользователя {userId}")
    public int deleteUser(final int userId) {
        return new Request<UserDTO>().delete(token, "/user/" + userId);
    }
}
