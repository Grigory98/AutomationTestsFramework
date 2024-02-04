import base.Request;
import base.Response;
import dto.House;
import dto.ParkingPlace;

import java.util.LinkedHashMap;

public class HouseClientImpl {

    private final String token = Token.authorization();

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
    public Response<House> createHouse(
            final int floorCount,
            final double price,
            final ParkingPlace[] parkingPlaces
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaces);

        return new Request<House>().Post(token, "/house", params, House.class);
    }

    /**
     * Изменить данные дома houseId
     */
    public Response<House> updateHouse(
            final int houseId,
            final int floorCount,
            final double price,
            final ParkingPlace[] parkingPlaces
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaces);

        return new Request<House>().Put(token, "/house/" + houseId, params, House.class);
    }

    /**
     * Удалить дом houseId
     */
    public int deleteHouse(final int houseId) {
        return new Request<House>().Delete(token, "/house/" + houseId);
    }

    /**
     * Заселить жильца userId в дом houseId
     */
    public Response<House> addLodger(final int houseId, final int userId) {
        return new Request<House>().Post(token, "/house/" + houseId + "/settle/" + userId, null, House.class);
    }

    /**
     * Выселить жильца userId из дома houseId
     */
    public Response<House> removeLodger(final int houseId, final int userId) {
        return new Request<House>().Post(token, "/house/" + houseId + "/evict/" + userId, null, House.class);
    }

    /**
     * Создать новое парковочное место
     */
    public ParkingPlace createParkingPlace(final boolean isWarm, final boolean isCovered, final int price) {
        return new ParkingPlace(isWarm, isCovered, price);
    }
}
