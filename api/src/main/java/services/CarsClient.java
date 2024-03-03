package services;

import dto.CarDTO;

public interface CarsClient {
    CarDTO[] getCars();

    CarDTO getCar(final int id);

    CarDTO[] getUsersCars(final int userId);

    CarDTO createCar(
            final String engineType,
            final String mark,
            final String model,
            final double price
    );

    CarDTO updateCar(
            final int id,
            final String engineType,
            final String mark,
            final String model,
            final double price
    );

    int deleteCar(final int carId);
}
