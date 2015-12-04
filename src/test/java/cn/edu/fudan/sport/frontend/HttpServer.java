package cn.edu.fudan.sport.frontend;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class HttpServer {

    private static HttpServer server = new HttpServer();

    private String host = "http://192.168.11.117:8081/sport";

    private HttpClient httpClient = HttpClientBuilder.create().build();

    public static HttpServer getInstance() {
        return server;
    }

    // Portraits Controller
    public byte[] getPortrait(Integer id) {
        String request = "/portraits/" + id;
        try {
            HttpGet httpGet = new HttpGet(host + request);
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n;
                while ((n = is.read(buf)) != -1)
                    bos.write(buf, 0, n);
                return bos.toByteArray();
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