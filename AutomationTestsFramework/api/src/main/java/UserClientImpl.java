import com.google.gson.Gson;
import config.Constants;
import maps.User;
import maps.UserInfo;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserClientImpl implements UserClient {

    private final Gson gson = new Gson();

    /***
     * Получение всех пользователей
     */
    @Override
    public Response<User[]> getUsers() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(Constants.URL + "/users");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String json = EntityUtils.toString(response.getEntity());
            User[] user = gson.fromJson(json, User[].class);
            return new Response<User[]>(response.getCode(), user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение пользователя по id
     */
    @Override
    public Response<User> getUser(int id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(Constants.URL + "/user/" + id);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String json = EntityUtils.toString(response.getEntity());
            User user = gson.fromJson(json, User.class);
            return new Response<User>(response.getCode(), user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получение информации о пользователе
     */
    @Override
    public Response<UserInfo> getUserInfo(int id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet(Constants.URL + "/user/" + id + "/Info");
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String json = EntityUtils.toString(response.getEntity());
            UserInfo userInfo = gson.fromJson(json, UserInfo.class);
            return new Response<UserInfo>(response.getCode(), userInfo);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Добавление нового пользователя
     */
    public Response<User> createUser(String firstName, String secondName, int age, String sex, double money) {
        String token = Token.authorization();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPost httpPost = new HttpPost(Constants.URL + "/user");
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("firstName", firstName));
            params.add(new BasicNameValuePair("secondName", secondName));
            params.add(new BasicNameValuePair("age", Integer.toString(age)));
            params.add(new BasicNameValuePair("sex", sex));
            params.add(new BasicNameValuePair("money", Double.toString(money)));
            httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

            httpPost.setHeader(HttpHeaders.AUTHORIZATION, token);
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setHeader(HttpHeaders.HOST, Constants.HOST);
            httpPost.setHeader(HttpHeaders.CONTENT_LENGTH, 200);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());
            User user = gson.fromJson(json, User.class);
            return new Response<User>(response.getCode(), user);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
