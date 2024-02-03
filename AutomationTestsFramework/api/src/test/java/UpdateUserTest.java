import base.Response;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UpdateUserTest {
    @Test
    public void updateUser() {
        UserClientImpl userClient = new UserClientImpl();

        Response<User> response = userClient.updateUser(6857, "Aboba", "Abobus", 35, "MALE", 1000);

        Assertions.assertEquals(202, response.getCode(), "Код должен быть 202");
    }
}
