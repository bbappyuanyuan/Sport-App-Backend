package cn.edu.fudan.sport.frontend;

import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.domain.Moment;
import cn.edu.fudan.sport.domain.Record;
import cn.edu.fudan.sport.vo.*;
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
import java.util.List;

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

    public Account getAccount(Integer id) {
        String request = "/accounts/" + id;
        try {
            HttpGet httpGet = new HttpGet(host + request);
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
    public List<Account> getFollowees(Integer follower) {
        String request = "/fans/" + follower + "/followees";
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                AccountsVo vo = objectMapper.readValue(body, AccountsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getAccounts();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean unfollowFan(Integer follower, Integer followee) {
        String request = "/fans/" + follower + "/followees/" + followee;
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

    public boolean followFan(Integer follower, Integer followee) {
        String request = "/fans/" + follower + "/followees/" + followee;
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

    public List<Account> getFollowers(Integer followee) {
        String request = "/fans/" + followee + "/followers";
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                AccountsVo vo = objectMapper.readValue(body, AccountsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getAccounts();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Moments Controller
    public List<Moment> getMoments(Integer id) {
        String request = "/moments/" + id;
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                MomentsVo vo = objectMapper.readValue(body, MomentsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getMoments();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Moment> getMoments(Integer id, Integer limit) {
        String request = "/moments/" + id;
        try {
            HttpGet httpGet = new HttpGet(new URIBuilder(host + request)
                    .addParameter("limit", String.valueOf(limit))
                    .build());
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                MomentsVo vo = objectMapper.readValue(body, MomentsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getMoments();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean postMoment(Integer id, String message) {
        String request = "/moments/" + id;
        try {
            HttpPost httpPost = new HttpPost(new URIBuilder(host + request)
                    .addParameter("message", message)
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

    // Portraits Controller
    public byte[] downloadPortrait(Integer id) {
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

    // Records Controller
    public List<Record> getRecords(Integer id) {
        String request = "/records/" + id;
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                RecordsVo vo = objectMapper.readValue(body, RecordsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getRecords();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Record> getRecords(Integer id, Integer limit) {
        String request = "/records/" + id;
        try {
            HttpGet httpGet = new HttpGet(new URIBuilder(host + request)
                    .addParameter("limit", String.valueOf(limit))
                    .build());
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                RecordsVo vo = objectMapper.readValue(body, RecordsVo.class);
                if (vo.getStatus() == 1)
                    return vo.getRecords();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean postRecord(Integer id, Integer duration, Double distance, Double maxSpeed, Integer steps) {
        String request = "/records/" + id;
        try {
            HttpPost httpPost = new HttpPost(new URIBuilder(host + request)
                    .addParameter("duration", String.valueOf(duration))
                    .addParameter("distance", String.valueOf(distance))
                    .addParameter("maxSpeed", String.valueOf(maxSpeed))
                    .addParameter("steps", String.valueOf(steps))
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
}