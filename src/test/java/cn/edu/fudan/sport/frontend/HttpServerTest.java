package cn.edu.fudan.sport.frontend;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    HttpServer server = HttpServer.getInstance();

    @Test
    public void testPortraitsController() {
        assertTrue(server.getPortrait(1) != null);
        if (server.getPortrait(1) != null)
            saveFile("testGetPortrait", server.getPortrait(1));
        assertTrue(server.uploadPortrait(2333, new File("pic")));
    }

    private void saveFile(String path, byte[] file) {
        try {
            DataOutputStream bos = new DataOutputStream(new FileOutputStream(path));
            bos.write(file);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}