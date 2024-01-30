import maps.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateUserTest extends MainTest {
    @Test
    public void createUser() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User> response = userClient.createUser("Biba", "Boba", 30, "MALE", 1000.25);

        Assertions.assertEquals(200, response.getCode());
    }
}
