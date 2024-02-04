import base.Response;
import dto.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.CarsClientImpl;

public class CreateCarTest {
    @Test
    public void createCar() {
        CarsClientImpl carsClient = new CarsClientImpl();

        Response<Car> response = carsClient.createCar("Electric", "BMWWW", "X5", 100000);

        Assertions.assertEquals(201, response.getCode(), "Код долже быть 201");
    }
}
