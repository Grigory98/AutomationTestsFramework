import maps.User;
import maps.UserInfo;

public interface UserClient {

    Response<User[]> getUsers();

    Response<User> getUser(int id);

    Response<UserInfo> getUserInfo(int id);

    Response<User> createUser(String firstName, String secondName, int age, String sex, double money );

}
