package steps;

import base.Response;
import dto.Car;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import services.CarsClientImpl;

public class CarSteps {
    CarsClientImpl carClient = new CarsClientImpl();
    @Step("Создать новую машину")
    public Car createCar(
            final String engineType,
            final String mark,
            final String model,
            final double price
    ) {
        Response<Car> response = carClient.createCar(engineType, mark, model, price);
        Assertions.assertEquals(201, response.getCode(), "Код должен быть 201");
        return response.getObject();
    }

    @Step("Удалить машину {carId}")
    public void deleteCar(final int carId) {
        int responseCode = carClient.deleteCar(carId);
        Assertions.assertEquals(204, responseCode, "Код должен быть 204");
    }
}
