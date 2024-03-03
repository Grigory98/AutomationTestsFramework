package services;

import dto.UserDTO;
import dto.UserInfoDTO;

public interface UserClient {

    UserDTO[] getUsers();

    UserDTO getUser(int id);

    UserInfoDTO getUserInfo(int id);

    UserDTO createUser(
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    );

    UserDTO updateUser(
            final int id,
            final String firstName,
            final String secondName,
            final int age, String sex,
            final double money
    );

    UserDTO addMoney(int userId, double amount);

    UserDTO buyCar(final int userId, final int carId);

    UserDTO sellCar(final int userId, final int carId);

    int deleteUser(final int userId);
}
