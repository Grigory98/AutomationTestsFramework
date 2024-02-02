import base.Response;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetUsersTest {
    @Test
    public void getUsersTest() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User[]> response = userClient.getUsers();

        Assertions.assertEquals(200, response.getCode(), "Код ответа 200");
    }

    @Test
    public void getUserInfoTest() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User> response = userClient.getUser(1);

        Assertions.assertEquals(200, response.getCode(), "Код ответа 200");
    }

    @Test
    public void addMoneyTest() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User> response = userClient.changeMoney(1, 1000);

        Assertions.assertEquals(200, response.getCode(), "Код ответа 200");
    }
}
