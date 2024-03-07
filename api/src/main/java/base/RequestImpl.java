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
import java.util.Map;

public class RequestImpl<T> implements Request<T> {
    private static final Gson gson = new Gson();

    public T get(String url, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(Constants.URL_DEV + url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
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

    public T post(String token, String url, Map<String, Object> params, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(Constants.URL_DEV + url);
            StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
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

    public T put(String token, String url, Map<String, Object> params, Class<T> responseType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(Constants.URL_DEV + url);
            StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPut.setEntity(stringEntity);

            httpPut.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPut.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse response = httpClient.execute(httpPut);
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

    public static int delete(String token, String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete httpDelete = new HttpDelete(Constants.URL_DEV + url);
            httpDelete.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpDelete.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            CloseableHttpResponse response = httpClient.execute(httpDelete);
            return response.getCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
