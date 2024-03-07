package services;

import dto.CarDTO;

public interface CarsClient {
    CarDTO[] getCars();

    CarDTO getCar(final int id);

    CarDTO[] getUsersCars(final int userId);

    CarDTO createCar(
            String engineType,
            String mark,
            String model,
            double price
    );

    CarDTO updateCar(
            int id,
            String engineType,
            String mark,
            String model,
            double price
    );

    int deleteCar(int carId);
}
