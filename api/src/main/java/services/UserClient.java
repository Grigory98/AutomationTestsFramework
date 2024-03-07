package services;

import dto.UserDTO;
import dto.UserInfoDTO;

public interface UserClient {

    UserDTO[] getUsers();

    UserDTO getUser(int id);

    UserInfoDTO getUserInfo(int id);

    UserDTO createUser(
            String firstName,
            String secondName,
            int age, String sex,
            double money
    );

    UserDTO updateUser(
            int id,
            String firstName,
            String secondName,
            int age, String sex,
            double money
    );

    UserDTO addMoney(int userId, double amount);

    UserDTO buyCar(int userId, int carId);

    UserDTO sellCar(int userId, int carId);

    int deleteUser(int userId);
}
