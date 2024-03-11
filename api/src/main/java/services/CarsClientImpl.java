package services;

import base.RequestImpl;
import dto.CarDTO;
import io.qameta.allure.Step;

import java.util.LinkedHashMap;

public class CarsClientImpl implements CarsClient {

    private final String token = Token.authorization();

    @Step("Получить все машины")
    public CarDTO[] getCars() {
        return RequestImpl.get("/cars", CarDTO[].class);
    }

    @Step("Получить машину с id = {id}")
    public CarDTO getCar(final int id) {
        return RequestImpl.get("/car/" + id, CarDTO.class);
    }

    @Step("Получить все машины у пользователя c id = {userId}")
    public CarDTO[] getUsersCars(final int userId) {
        return RequestImpl.get("/usersCars/" + userId, CarDTO[].class);
    }

    @Step("Создать новую машину")
    public CarDTO createCar(
            String engineType,
            String mark,
            String model,
            double price
    ) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("engineType", engineType);
        params.put("mark", mark);
        params.put("model", model);
        params.put("price", price);
        return RequestImpl.post(token, "/car", params, CarDTO.class);
    }

    @Step("Изменить данные машины с id = {id}")
    public CarDTO updateCar(
            int id,
            String engineType,
            String mark,
            String model,
            double price
    ) {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("engineType", engineType);
        params.put("mark", mark);
        params.put("model", model);
        params.put("price", price);
        return RequestImpl.put(token, "/car/" + id, params, CarDTO.class);
    }

    @Step("Удалить машину {carId}")
    public int deleteCar(int carId) {
        return RequestImpl.delete(token, "/car/" + carId);
    }

}
