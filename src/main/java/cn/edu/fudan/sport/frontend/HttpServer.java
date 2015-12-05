package cn.edu.fudan.sport.frontend;

import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.view.AccountVo;
import cn.edu.fudan.sport.view.BaseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class HttpServer {

    private static HttpServer server = new HttpServer();

    private String host = "http://localhost:8081/sport";

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ObjectMapper objectMapper = new ObjectMapper();

    public static HttpServer getInstance() {
        return server;
    }

    // Accounts Controller
    public Account loginAccount(String email, String password) {
        String request = "/accounts";
        try {
            HttpGet httpGet = new HttpGet(new URIBuilder(host + request)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .build());
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                AccountVo vo = objectMapper.readValue(body, AccountVo.class);
                if (vo.getStatus() == 1)
                    return vo.getAccount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerAccount(String email, String password, String gender, Double height, Double weight) {
        String request = "/accounts";
        try {
            HttpPost httpPost = new HttpPost(new URIBuilder(host + request)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .addParameter("gender", gender)
                    .addParameter("height", String.valueOf(height))
                    .addParameter("weight", String.valueOf(weight))
                    .build());
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                BaseVo vo = objectMapper.readValue(body, BaseVo.class);
                if (vo.getStatus() == 1)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAccount(Integer id, String password, String gender, Double height, Double weight) {
        String request = "/accounts/" + id;
        try {
            HttpPut httpPut = new HttpPut(new URIBuilder(host + request)
                    .addParameter("password", password)
                    .addParameter("gender", gender)
                    .addParameter("height", String.valueOf(height))
                    .addParameter("weight", String.valueOf(weight))
                    .build());
            HttpResponse response = httpClient.execute(httpPut);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                BaseVo vo = objectMapper.readValue(body, BaseVo.class);
                if (vo.getStatus() == 1)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Fans Controller
    public boolean unfollowFan(Integer id, Integer id2) {
        String request = "/fans/" + id + "/" + id2;
        try {
            HttpDelete httpDelete = new HttpDelete(host + request);
            HttpResponse response = httpClient.execute(httpDelete);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                BaseVo vo = objectMapper.readValue(body, BaseVo.class);
                if (vo.getStatus() == 1)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean followFan(Integer id, Integer id2) {
        String request = "/fans/" + id + "/" + id2;
        try {
            HttpPost httpPost = new HttpPost(host + request);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                BaseVo vo = objectMapper.readValue(body, BaseVo.class);
                if (vo.getStatus() == 1)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Portraits Controller
    public byte[] getPortrait(Integer id) {
        String request = "/portraits/" + id;
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toByteArray(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean uploadPortrait(Integer id, File file) {
        String request = "/portraits/" + id;
        try {
            HttpPost httpPost = new HttpPost(host + request);
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName())
                    .build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}