import base.Response;
import dto.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CarsClientImpl;

public class GetCarsTest {
    @Test
    public void getCarsTest() {
        CarsClientImpl carsClient = new CarsClientImpl();

        Response<Car[]> response = carsClient.getCars();

        Assertions.assertEquals(200, response.getCode(), "Код должен быть 200");

        Response<Car> response1 = carsClient.getCar(1);

        Assertions.assertEquals(200, response1.getCode(), "Код должен быть 200");
    }
}
