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
import java.util.LinkedHashMap;

public interface IRequest<T> {
    T get(final String url, Class<T> responseType);

    T post(final String token, final String url, final LinkedHashMap<String, Object> params, Class<T> responseType);

    T put(final String token, final String url, final LinkedHashMap<String, Object> params, Class<T> responseType);

    int delete(final String token, final String url);

    default T post(final String url, final LinkedHashMap<String, Object> params, Class<T> responseType) {
        final Gson gson = new Gson();
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPost httpPost = new HttpPost(Constants.URL_DEV + url);
            final StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            final CloseableHttpResponse response = httpClient.execute(httpPost);

            final T responseBody = gson.fromJson(
                    EntityUtils.toString(response.getEntity()),
                    responseType
            );
            final Response<T> responseEntity = new Response<>(response.getCode(), responseBody);
            responseEntity.checkResultCode();

            return responseBody;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
