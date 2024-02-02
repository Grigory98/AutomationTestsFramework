import base.Request;
import base.Response;
import com.google.gson.Gson;
import dto.User;
import dto.UserInfo;

import java.util.LinkedHashMap;

public class UserClientImpl implements UserClient {

    private final Gson gson = new Gson();

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
        return new Request<UserInfo>().Get("/user/" + id + "/Info", UserInfo.class);
    }

    /**
     * Добавление нового пользователя
     */
    public Response<User> createUser(String firstName, String secondName, int age, String sex, double money) {
        String token = Token.authorization();
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("firstName", firstName);
        params.put("secondName", secondName);
        params.put("age", Integer.toString(age));
        params.put("sex", sex);
        params.put("money", Double.toString(money));

        return new Request<User>().Post(token, "/user", params, User.class);
    }
}
