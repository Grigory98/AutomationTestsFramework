package services;

import base.Request;
import base.Response;
import dto.User;
import dto.UserInfo;

import java.util.LinkedHashMap;

public class UserClientImpl implements UserClient {

    private final String token = Token.authorization();

    /***
     * Получение всех пользователей
     */
    @Override
    public Response<User[]> getUsers() {
        return new Request<User[]>().Get("/users", User[].class);
    }

    /**
     * Получение пользователя по id
     */
    @Override
    public Response<User> getUser(int id) {
        return new Request<User>().Get("/user/" + id, User.class);
    }

    /**
     * Получение информации о пользователе
     */
    @Override
    public Response<UserInfo> getUserInfo(int id) {
        return new Request<UserInfo>().Get("/user/" + id + "/info", UserInfo.class);
    }

    /**
     * Добавление нового пользователя
     */
    public Response<User> createUser(
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

        return new Request<User>().Post(token, "/user", params, User.class);
    }

    /**
     * Обновление данных пользователя
     */
    public Response<User> updateUser(
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

        return new Request<User>().Put(token, "/user/" + id, params, User.class);
    }


    /**
     * Добавление amount денежных средств пользователю userId
     */
    public Response<User> addMoney(int userId, double amount) {
        String url = "/user/" + userId + "/money/" + amount;
        return new Request<User>().Post(token, url, null, User.class);
    }

    /**
     * Купить пользователю userId машину carId
     */
    public Response<User> buyCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/buyCar/" + carId;
        return new Request<User>().Post(token, url, null, User.class);
    }

    /**
     * Продать машину carId у пользователя userId
     */
    public Response<User> sellCar(final int userId, final int carId) {
        String url = "/user/" + userId + "/sellCar/" + carId;
        return new Request<User>().Post(token, url, null, User.class);
    }

    /**
     * Удаление пользователя
     */
    public int deleteUser(final int userId) {
        return new Request<User>().Delete(token, "/user/" + userId);
    }
}
