package services;

import base.Request;
import base.Response;
import dto.Car;

import java.util.LinkedHashMap;

public class CarsClientImpl implements CarsClient {

    private final String token = Token.authorization();

    /**
     * Получить все машины
     */
    public Response<Car[]> getCars() {
        return new Request<Car[]>().Get("/cars", Car[].class);
    }

    /**
     * Получить машину по id
     */
    public Response<Car> getCar(final int id) {
        return new Request<Car>().Get("/car/" + id, Car.class);
    }

    /**
     * Получить все машины у userId пользователя
     */
    public Response<Car[]> getUsersCars(final int userId) {
        return new Request<Car[]>().Get("/usersCars/" + userId, Car[].class);
    }

    /**
     * Создать новую машину
     */
    public Response<Car> createCar(
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

        return new Request<Car>().Post(token, "/car", params, Car.class);
    }

    /**
     * Изменить данные машины по id
     */
    public Response<Car> updateCar(
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

        return new Request<Car>().Put(token, "/car/" + id, params, Car.class);
    }

    /**
     * Удалить машину
     */
    public int deleteCar(final int carId) {
        return new Request<Car>().Delete(token, "/car/" + carId);
    }

}
