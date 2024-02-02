import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteUserTest {
    @Test
    public void deleteUser() {
        UserClientImpl userClient = new UserClientImpl();

        int code = userClient.deleteUser(6863);

        Assertions.assertEquals(204, code, "Код равен 204");
    }
}
