package dto;

import com.google.gson.annotations.SerializedName;

public class TokenMap {
    @SerializedName("access_token")
    private String accessToken;

    public String getToken() {
        return accessToken;
    }
}
