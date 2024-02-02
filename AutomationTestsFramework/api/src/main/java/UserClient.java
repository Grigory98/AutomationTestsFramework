import base.Response;
import dto.User;
import dto.UserInfo;

public interface UserClient {

    Response<User[]> getUsers();

    Response<User> getUser(int id);

    Response<UserInfo> getUserInfo(int id);

    Response<User> createUser(String firstName, String secondName, int age, String sex, double money );

}
