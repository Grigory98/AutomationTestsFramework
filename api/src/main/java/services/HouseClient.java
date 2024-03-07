package services;

import dto.HouseDTO;
import dto.ParkingPlaceDTO;

public interface HouseClient {
    HouseDTO[] getHouses();

    HouseDTO getHouse(int houseId);

    HouseDTO createHouse(
            int floorCount,
            double price,
            ParkingPlaceDTO[] parkingPlaceDTOS
    );

    HouseDTO updateHouse(
            int houseId,
            int floorCount,
            double price,
            ParkingPlaceDTO[] parkingPlaceDTOS
    );

    int deleteHouse(int houseId);

    HouseDTO addLodger(int houseId, int userId);

    HouseDTO removeLodger(int houseId, int userId);

    ParkingPlaceDTO createParkingPlace(
            boolean isWarm,
            boolean isCovered,
            int price
    );
}
