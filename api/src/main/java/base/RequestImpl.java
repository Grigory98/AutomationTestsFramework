package base;

import config.Constants;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.Map;

public class RequestImpl<T> implements Request<T> {
    public static <T> T get(String url, Class<T> responseType) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(Constants.URL_DEV + url);
            String responseContent = httpClient.execute(httpGet, response -> {
                Response.checkResultCode(response.getCode());
                return EntityUtils.toString(response.getEntity());
            });
            return gson.fromJson(responseContent, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T post(String token, String url, Map<String, Object> params, Class<T> responseType) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(Constants.URL_DEV + url);
            StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            String responseContent = httpClient.execute(httpPost, response -> {
                Response.checkResultCode(response.getCode());
                return EntityUtils.toString(response.getEntity());
            });
            return gson.fromJson(responseContent, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T put(String token, String url, Map<String, Object> params, Class<T> responseType) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(Constants.URL_DEV + url);
            StringEntity stringEntity = new StringEntity(gson.toJson(params));
            httpPut.setEntity(stringEntity);
            httpPut.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPut.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            String responseContent = httpClient.execute(httpPut, response -> {
                Response.checkResultCode(response.getCode());
                return EntityUtils.toString(response.getEntity());
            });
            return gson.fromJson(responseContent, responseType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int delete(String token, String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
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
