package services;

import base.RequestImpl;
import config.Constants;
import dto.TokenMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Token {
    public static String authorization() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", Constants.LOGIN);
        params.put("password", Constants.PASSWORD);
        TokenMap response = new RequestImpl<TokenMap>().post(null, "/login", params, TokenMap.class);
        return "Bearer " + response.getToken();
    }
}
