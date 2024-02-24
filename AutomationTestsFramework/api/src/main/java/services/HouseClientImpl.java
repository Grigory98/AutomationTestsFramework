package services;

import base.Request;
import dto.HouseDTO;
import dto.ParkingPlaceDTO;
import io.qameta.allure.Step;

import java.util.LinkedHashMap;

public class HouseClientImpl implements HouseClient {

    private final String token = Token.authorization();

    @Step("Получить все дома")
    public HouseDTO[] getHouses() {
        return new Request<HouseDTO[]>().Get("/houses", HouseDTO[].class);
    }

    @Step("Получить дом {houseId}")
    public HouseDTO getHouse(final int houseId) {
        return new Request<HouseDTO>().Get("/house/" + houseId, HouseDTO.class);
    }

    @Step("Создать новый дом")
    public HouseDTO createHouse(
            final int floorCount,
            final double price,
            final ParkingPlaceDTO[] parkingPlaceDTOS
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaceDTOS);

        return new Request<HouseDTO>().Post(token, "/house", params, HouseDTO.class);
    }

    @Step("Изменить данные дома {houseId}")
    public HouseDTO updateHouse(
            final int houseId,
            final int floorCount,
            final double price,
            final ParkingPlaceDTO[] parkingPlaceDTOS
    ) {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaceDTOS);

        return new Request<HouseDTO>().Put(token, "/house/" + houseId, params, HouseDTO.class);
    }

    @Step("Удалить дом {houseId}")
    public int deleteHouse(final int houseId) {
        return new Request<HouseDTO>().Delete(token, "/house/" + houseId);
    }

    @Step("Заселить жильца {userId} в дом {houseId}")
    public HouseDTO addLodger(final int houseId, final int userId) {
        return new Request<HouseDTO>().Post(token, "/house/" + houseId + "/settle/" + userId, null, HouseDTO.class);
    }

    @Step("Выселить жильца {userId} из дома {houseId}")
    public HouseDTO removeLodger(final int houseId, final int userId) {
        return new Request<HouseDTO>().Post(token, "/house/" + houseId + "/evict/" + userId, null, HouseDTO.class);
    }

    @Step("Создать новое парковочное место")
    public ParkingPlaceDTO createParkingPlace(final boolean isWarm, final boolean isCovered, final int price) {
        return new ParkingPlaceDTO(isWarm, isCovered, price);
    }
}
