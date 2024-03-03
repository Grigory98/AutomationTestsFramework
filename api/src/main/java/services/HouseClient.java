package services;

import dto.HouseDTO;
import dto.ParkingPlaceDTO;

public interface HouseClient {
    HouseDTO[] getHouses();

    HouseDTO getHouse(final int houseId);

    HouseDTO createHouse(
            final int floorCount,
            final double price,
            final ParkingPlaceDTO[] parkingPlaceDTOS
    );

    HouseDTO updateHouse(
            final int houseId,
            final int floorCount,
            final double price,
            final ParkingPlaceDTO[] parkingPlaceDTOS
    );

    int deleteHouse(final int houseId);

    HouseDTO addLodger(final int houseId, final int userId);

    HouseDTO removeLodger(final int houseId, final int userId);

    ParkingPlaceDTO createParkingPlace(
            final boolean isWarm,
            final boolean isCovered,
            final int price
    );
}
