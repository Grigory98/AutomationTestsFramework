package services;

import base.Request;
import config.Constants;
import dto.TokenMap;

import java.util.LinkedHashMap;

public class Token {
    public static String authorization() {
        final LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("username", Constants.LOGIN);
        params.put("password", Constants.PASSWORD);
        TokenMap response = new Request<TokenMap>().Post(null, "/login", params, TokenMap.class);
        return "Bearer " + response.getToken();
    }
}
