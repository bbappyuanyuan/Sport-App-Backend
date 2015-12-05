package cn.edu.fudan.sport.frontend;

import cn.edu.fudan.sport.domain.Account;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    HttpServer server = HttpServer.getInstance();

    @Test
    public void testAccountsController() {
        server.registerAccount("test@test.com", "123456", "male", 175.0, 60.0);
        Account account = server.loginAccount("test@test.com", "123456");
        Account account1 = server.loginAccount(account.getId());
        assertTrue(account1.getEmail().equals(account.getEmail()));
        assertTrue(account.getEmail().equals("test@test.com"));
        assertTrue(account.getHeight().equals(175.0));
        account.setPassword("new");
        account.setGender("female");
        server.updateAccount(account.getId(), account.getPassword(), account.getGender(), account.getHeight(), account.getWeight());
        Account account2 = server.loginAccount(account.getEmail(), account.getPassword());
        assertTrue(account2.getPassword().equals(account.getPassword()));
        assertTrue(account2.getGender().equals(account.getGender()));
    }

    @Test
    public void testFansController() {
        int s = server.getFans(1).size();
        server.followFan(1, 4000);
        server.followFan(1, 4001);
        assertTrue(server.getFans(1).size() == s + 2);
        server.unfollowFan(1, 4001);
        assertTrue(server.getFans(1).size() == s + 1);
        server.unfollowFan(1, 4000);
        assertTrue(server.getFans(1).size() == s);

    }

    @Test
    @Ignore
    public void testPortraitsController() {
        assertTrue(server.downloadPortrait(1) != null);
        saveFile("testGetPortrait", server.downloadPortrait(1));
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