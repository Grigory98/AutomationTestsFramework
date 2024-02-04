import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteCarTest {
    @Test
    public void deleteCar() {
        CarsClientImpl carsClient = new CarsClientImpl();

        int code = carsClient.deleteCar(1140);

        Assertions.assertEquals(204, code, "Код должен быть 204");
    }
}
