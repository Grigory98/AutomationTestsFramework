package base;

import com.google.gson.Gson;
import config.Constants;
import org.apache.hc.client5.http.classic.methods.HttpGet;
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

public class Request<T> {

    private final Gson gson = new Gson();

    public Response<T> Get(final String url, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(Constants.URL + url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String json = EntityUtils.toString(response.getEntity());
            T entity = gson.fromJson(json, responseType);
            return new Response<T>(response.getCode(), entity);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Response<T> Post(final String token, final String url, final LinkedHashMap<String, String> params, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPost httpPost = new HttpPost(Constants.URL + url);
            String entityString = gson.toJson(params);

            StringEntity stringEntity = new StringEntity(entityString);
            httpPost.setEntity(stringEntity);

            httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse response = httpClient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());
            T entity = gson.fromJson(json, responseType);
            return new Response<T>(response.getCode(), entity);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
