package services;

import base.Response;
import dto.House;
import dto.ParkingPlace;

public interface HouseClient {
    Response<House[]> getHouses();

    Response<House> getHouse(final int houseId);

    Response<House> createHouse(
            final int floorCount,
            final double price,
            final ParkingPlace[] parkingPlaces
    );

    Response<House> updateHouse(
            final int houseId,
            final int floorCount,
            final double price,
            final ParkingPlace[] parkingPlaces
    );

    int deleteHouse(final int houseId);

    Response<House> addLodger(final int houseId, final int userId);

    Response<House> removeLodger(final int houseId, final int userId);

    ParkingPlace createParkingPlace(
            final boolean isWarm,
            final boolean isCovered,
            final int price
    );
}
