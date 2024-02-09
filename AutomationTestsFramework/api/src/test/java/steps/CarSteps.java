package steps;

import dto.CarDTO;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import services.CarsClientImpl;

public class CarSteps {
    CarsClientImpl carClient = new CarsClientImpl();
    @Step("Создать новую машину")
    public CarDTO createCar(
            final String engineType,
            final String mark,
            final String model,
            final double price
    ) {
        CarDTO response = carClient.createCar(engineType, mark, model, price);
        return response;
    }

    @Step("Удалить машину {carId}")
    public void deleteCar(final int carId) {
        int responseCode = carClient.deleteCar(carId);
        Assertions.assertEquals(204, responseCode, "Код должен быть 204");
    }
}
