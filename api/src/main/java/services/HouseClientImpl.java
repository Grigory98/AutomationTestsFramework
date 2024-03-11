package services;

import base.RequestImpl;
import dto.HouseDTO;
import dto.ParkingPlaceDTO;
import io.qameta.allure.Step;

import java.util.LinkedHashMap;
import java.util.Map;

public class HouseClientImpl implements HouseClient {
    private final String token = Token.authorization();

    @Step("Получить все дома")
    public HouseDTO[] getHouses() {
        return RequestImpl.get("/houses", HouseDTO[].class);
    }

    @Step("Получить дом {houseId}")
    public HouseDTO getHouse(int houseId) {
        return RequestImpl.get("/house/" + houseId, HouseDTO.class);
    }

    @Step("Создать новый дом")
    public HouseDTO createHouse(
            int floorCount,
            double price,
            ParkingPlaceDTO[] parkingPlaceDTOS
    ) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaceDTOS);
        return RequestImpl.post(token, "/house", params, HouseDTO.class);
    }

    @Step("Изменить данные дома {houseId}")
    public HouseDTO updateHouse(
            int houseId,
            int floorCount,
            double price,
            ParkingPlaceDTO[] parkingPlaceDTOS
    ) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("floorCount", floorCount);
        params.put("price", price);
        params.put("parkingPlaces", parkingPlaceDTOS);
        return RequestImpl.put(token, "/house/" + houseId, params, HouseDTO.class);
    }

    @Step("Удалить дом {houseId}")
    public int deleteHouse(int houseId) {
        return RequestImpl.delete(token, "/house/" + houseId);
    }

    @Step("Заселить жильца {userId} в дом {houseId}")
    public HouseDTO addLodger(int houseId, int userId) {
        return RequestImpl.post(token, "/house/" + houseId + "/settle/" + userId, null, HouseDTO.class);
    }

    @Step("Выселить жильца {userId} из дома {houseId}")
    public HouseDTO removeLodger(int houseId, int userId) {
        return RequestImpl.post(token, "/house/" + houseId + "/evict/" + userId, null, HouseDTO.class);
    }

    @Step("Создать новое парковочное место")
    public ParkingPlaceDTO createParkingPlace(boolean isWarm, boolean isCovered, int price) {
        return new ParkingPlaceDTO(isWarm, isCovered, price);
    }
}
