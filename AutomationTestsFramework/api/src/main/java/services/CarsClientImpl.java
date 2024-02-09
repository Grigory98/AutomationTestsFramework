package services;

import base.Request;
import dto.CarDTO;

import java.util.LinkedHashMap;

public class CarsClientImpl implements CarsClient {

    private final String token = Token.authorization();

    /**
     * Получить все машины
     */
    public CarDTO[] getCars() {
        return new Request<CarDTO[]>().Get("/cars", CarDTO[].class);
    }

    /**
     * Получить машину по id
     */
    public CarDTO getCar(final int id) {
        return new Request<CarDTO>().Get("/car/" + id, CarDTO.class);
    }

    /**
     * Получить все машины у userId пользователя
     */
    public CarDTO[] getUsersCars(final int userId) {
        return new Request<CarDTO[]>().Get("/usersCars/" + userId, CarDTO[].class);
    }

    /**
     * Создать новую машину
     */
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

        return new Request<CarDTO>().Post(token, "/car", params, CarDTO.class);
    }

    /**
     * Изменить данные машины по id
     */
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

        return new Request<CarDTO>().Put(token, "/car/" + id, params, CarDTO.class);
    }

    /**
     * Удалить машину
     */
    public int deleteCar(final int carId) {
        return new Request<CarDTO>().Delete(token, "/car/" + carId);
    }

}
