package base;

import com.google.gson.Gson;
import config.Constants;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
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

    public T get(final String url, Class<T> responseType) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(Constants.URL_DEV + url);
        try {
            final CloseableHttpResponse response = httpClient.execute(httpGet);
            final T responseBody = gson.fromJson(
                    EntityUtils.toString(response.getEntity()),
                    responseType
            );
            final Response<T> responseEntity = new Response<T>(response.getCode(), responseBody);
            responseEntity.checkResultCode();

            return responseBody;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public T post(final String token, final String url, final LinkedHashMap<String, Object> params, Class<T> responseType) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPost httpPost = new HttpPost(Constants.URL_DEV + url);
            final StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            final CloseableHttpResponse response = httpClient.execute(httpPost);

            final T responseBody = gson.fromJson(
                    EntityUtils.toString(response.getEntity()),
                    responseType
            );
            final Response<T> responseEntity = new Response<T>(response.getCode(), responseBody);
            responseEntity.checkResultCode();

            return responseBody;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public T put(final String token, final String url, final LinkedHashMap<String, Object> params, Class<T> responseType) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPut httpPut = new HttpPut(Constants.URL_DEV + url);
            final StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPut.setEntity(stringEntity);

            httpPut.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPut.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            final CloseableHttpResponse response = httpClient.execute(httpPut);
            final T responseBody = gson.fromJson(
                    EntityUtils.toString(response.getEntity()),
                    responseType
            );
            final Response<T> responseEntity = new Response<T>(response.getCode(), responseBody);
            responseEntity.checkResultCode();

            return responseBody;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(final String token, final String url) {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpDelete httpDelete = new HttpDelete(Constants.URL_DEV + url);
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpDelete.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            final CloseableHttpResponse response = httpClient.execute(httpDelete);
            return response.getCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
