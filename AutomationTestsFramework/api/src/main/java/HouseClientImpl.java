import base.Request;
import base.Response;
import dto.House;
import dto.ParkingPlace;

import java.util.LinkedHashMap;

public class HouseClientImpl {

    /**
     * Получить все дома
     */
    public Response<House[]> getHouses() {
        return new Request<House[]>().Get("/houses", House[].class);
    }

    /**
     * Получить дом по id
     */
    public Response<House> getHouse(final int houseId) {
        return new Request<House>().Get("/house/" + houseId, House.class);
    }

    /**
     * Создать новый дом
     */
    public Response<House> createHouse(final int floorCount, final double price, final ParkingPlace[] parkingPlaces) {
        String token = Token.authorization();

        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaces);

        return new Request<House>().Post(token, "/house", params, House.class);
    }

    /**
     * Создать новое парковочное место
     */
    public ParkingPlace createParkingPlace(final boolean isWarm, final boolean isCovered, final int price) {
        return new ParkingPlace(isWarm, isCovered, price);
    }
}
