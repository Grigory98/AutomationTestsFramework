import com.google.gson.Gson;
import config.Constants;
import maps.TokenMap;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Token {

    private final Gson gson = new Gson();

    public Response<TokenMap> authorization() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            final HttpPost httpPost = new HttpPost(Constants.URL + "/user");
            List<NameValuePair> params = new ArrayList<>(2);
            params.add(new BasicNameValuePair("username", Constants.LOGIN));
            params.add(new BasicNameValuePair("password", Constants.PASSWORD));

            httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            String json = EntityUtils.toString(response.getEntity());
            TokenMap token = gson.fromJson(json, TokenMap.class);
            return new Response<TokenMap>(response.getCode(), token);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
