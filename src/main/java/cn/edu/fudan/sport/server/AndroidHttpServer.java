package cn.edu.fudan.sport.server;

import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.domain.Moment;
import cn.edu.fudan.sport.domain.Record;
import cn.edu.fudan.sport.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpPut;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;

import java.io.File;
import java.util.List;

public class AndroidHttpServer {

    private String host = "http://localhost:8081/sport";

    private HttpClient httpClient = HttpClientBuilder.create().build();
    private ObjectMapper objectMapper = new ObjectMapper();

    public AndroidHttpServer() {
    }

    public AndroidHttpServer(String host) {
        this.host = host;
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

    public boolean registerAccount(String email, String password, String username, String gender, Double height,
                                   Double weight) {
        String request = "/accounts";
        try {
            HttpPost httpPost = new HttpPost(new URIBuilder(host + request)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .addParameter("username", username)
                    .addParameter("gender", gender)
                    .addParameter("height", String.valueOf(height))
                    .addParameter("weight", String.valueOf(weight))
                    .build());
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                IdVo vo = objectMapper.readValue(body, IdVo.class);
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

    public List<Account> getAccounts(String username) {
        String request = "/accounts";
        try {
            HttpGet httpGet = new HttpGet(new URIBuilder(host + request)
                    .addParameter("username", username)
                    .build());
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

    public boolean updateAccount(Integer id, String password, String username, String gender, Double height,
                                 Double weight) {
        String request = "/accounts/" + id;
        try {
            HttpPut httpPut = new HttpPut(new URIBuilder(host + request)
                    .addParameter("password", password)
                    .addParameter("username", username)
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

    public boolean postMoment(Integer id, String message, File photo) {
        String request = "/moments/" + id;
        try {
            HttpPost httpPost = new HttpPost(new URIBuilder(host + request)
                    .addParameter("message", message)
                    .addParameter("hasPhoto", String.valueOf(photo != null))
                    .build());
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String body = EntityUtils.toString(entity);
                IdVo vo = objectMapper.readValue(body, IdVo.class);
                if (vo.getStatus() == 1) {
                    if (photo == null)
                        return true;
                    if (uploadPhoto(vo.getId(), photo))
                        return true;
                }
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

    // Photos Controller
    public byte[] downloadPhoto(Integer id) {
        String request = "/photos/" + id;
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

    private boolean uploadPhoto(Integer id, File file) {
        String request = "/photos/" + id;
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