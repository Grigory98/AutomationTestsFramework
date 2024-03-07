package base;

import com.google.gson.Gson;
import config.Constants;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

public interface Request<T> {
    Gson gson = new Gson();
    T get(String url, Class<T> responseType);

    T post(String token, String url, Map<String, Object> params, Class<T> responseType);

    T put(String token, String url, Map<String, Object> params, Class<T> responseType);

    int delete(String token, String url);

    default T post(String url, Map<String, Object> params, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constants.URL_DEV + url);
            StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse response = httpClient.execute(httpPost);

            T responseBody = gson.fromJson(
                    EntityUtils.toString(response.getEntity()),
                    responseType
            );
            Response.checkResultCode(response.getCode());
            return responseBody;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
