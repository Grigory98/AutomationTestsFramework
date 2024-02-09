package services;

import base.Request;
import dto.UserDTO;
import dto.UserInfoDTO;

import java.util.LinkedHashMap;

public class UserClientImpl implements UserClient {

    private final String token = Token.authorization();

    /***
     * Получение всех пользователей
     */
    @Override
    public UserDTO[] getUsers() {
        return new Request<UserDTO[]>().Get("/users", UserDTO[].class);
    }

    /**
     * Получение пользователя по id
     */
    @Override
    public UserDTO getUser(int id) {
        return new Request<UserDTO>().Get("/user/" + id, UserDTO.class);
    }

    /**
     * Получение информации о пользователе
     */
    @Override
    public UserInfoDTO getUserInfo(int id) {
        return new Request<UserInfoDTO>().Get("/user/" + id + "/info", UserInfoDTO.class);
    }

    /**
     * Добавление нового пользователя
     */
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

        return new Request<UserDTO>().Post(token, "/user", params, UserDTO.class);
    }

    /**
     * Обновление данных пользователя
     */
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

        return new Request<UserDTO>().Put(token, "/user/" + id, params, UserDTO.class);
    }


    /**
     * Добавление amount денежных средств пользователю userId
     */
    public UserDTO addMoney(int userId, double amount) {
        String url = "/user/" + userId + "/money/" + amount;
        return new Request<UserDTO>().Post(token, url, null, UserDTO.class);
    }

    /**
     * Купить пользователю userId машину carId
     */
    public UserDTO buyCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/buyCar/" + carId;
        return new Request<UserDTO>().Post(token, url, null, UserDTO.class);
    }

    /**
     * Продать машину carId у пользователя userId
     */
    public UserDTO sellCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/sellCar/" + carId;
        return new Request<UserDTO>().Post(token, url, null, UserDTO.class);
    }

    /**
     * Удаление пользователя
     */
    public int deleteUser(final int userId) {
        return new Request<UserDTO>().Delete(token, "/user/" + userId);
    }
}
