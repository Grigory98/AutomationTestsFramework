package services;

import base.Request;
import dto.CarDTO;
import io.qameta.allure.Step;

import java.util.LinkedHashMap;

public class CarsClientImpl implements CarsClient {

    private final String token = Token.authorization();

    @Step("Получить все машины")
    public CarDTO[] getCars() {
        return new Request<CarDTO[]>().get("/cars", CarDTO[].class);
    }

    @Step("Получить машину с id = {id}")
    public CarDTO getCar(final int id) {
        return new Request<CarDTO>().get("/car/" + id, CarDTO.class);
    }

    @Step("Получить все машины у пользователя c id = {userId}")
    public CarDTO[] getUsersCars(final int userId) {
        return new Request<CarDTO[]>().get("/usersCars/" + userId, CarDTO[].class);
    }

    @Step("Создать новую машину")
    public CarDTO createCar(
            final String engineType,
            final String mark,
            final String model,
            final double price
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("engineType", engineType);
        params.put("mark", mark);
        params.put("model", model);
        params.put("price", price);

        return new Request<CarDTO>().post(token, "/car", params, CarDTO.class);
    }

    @Step("Изменить данные машины с id = {id}")
    public CarDTO updateCar(
            final int id,
            final String engineType,
            final String mark,
            final String model,
            final double price
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("engineType", engineType);
        params.put("mark", mark);
        params.put("model", model);
        params.put("price", price);

        return new Request<CarDTO>().put(token, "/car/" + id, params, CarDTO.class);
    }

    @Step("Удалить машину {carId}")
    public int deleteCar(final int carId) {
        return new Request<CarDTO>().delete(token, "/car/" + carId);
    }

}
