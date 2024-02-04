import base.Response;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserClientImpl;

public class CreateUserTest {
    @Test
    public void createUser() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User> response = userClient.createUser("Aaa", "Bbb", 30, "MALE", 10000);

        Assertions.assertEquals(201, response.getCode());
    }
}
