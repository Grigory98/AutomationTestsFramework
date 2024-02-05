package services;

import base.Response;
import dto.User;
import dto.UserInfo;

public interface UserClient {

    Response<User[]> getUsers();

    Response<User> getUser(int id);

    Response<UserInfo> getUserInfo(int id);

    Response<User> createUser(
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    );

    Response<User> updateUser(
            final int id,
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    );

    Response<User> addMoney(int userId, double amount);

    Response<User> buyCar(final int userId, final int carId);

    Response<User> sellCar(final int userId, final int carId);

    int deleteUser(final int userId);
}
