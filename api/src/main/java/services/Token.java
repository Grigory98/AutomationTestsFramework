package services;

import base.RequestImpl;
import dto.TokenMap;

import java.util.LinkedHashMap;
import java.util.Map;

public class Token {
    public static String authorization() {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", System.getProperty("username"));
        params.put("password", System.getProperty("password"));
        TokenMap response = new RequestImpl<TokenMap>().post(null, "/login", params, TokenMap.class);
        return "Bearer " + response.getToken();
    }
}
