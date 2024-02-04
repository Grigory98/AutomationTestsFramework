package services;

import base.Response;
import dto.Car;

public interface CarsClient {
    Response<Car[]> getCars();

    Response<Car> getCar(final int id);

    Response<Car[]> getUsersCars(final int userId);

    Response<Car> createCar(
            final String engineType,
            final String mark,
            final String model,
            final double price
    );

    Response<Car> updateCar(
            final int id,
            final String engineType,
            final String mark,
            final String model,
            final double price
    );

    int deleteCar(final int carId);
}
