package cn.edu.fudan.sport.server;

import cn.edu.fudan.sport.domain.Account;
import org.junit.Ignore;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    HttpServer server = new HttpServer();

    @Test
    @Ignore
    public void testAccountsController() {
        assertTrue(server.registerAccount("code_test@code_test.com", "123456", "un", "male", 175.0, 60.0));
        Account account = server.loginAccount("code_test@code_test.com", "123456");
        Account account1 = server.getAccount(account.getId());
        assertTrue(account1.getEmail().equals(account.getEmail()));
        assertTrue(account.getEmail().equals("code_test@code_test.com"));
        assertTrue(account.getHeight().equals(175.0));
        account.setPassword("new");
        account.setUsername("un2");
        account.setGender("female");
        assertTrue(server.updateAccount(account.getId(), account.getPassword(), account.getUsername(), account.getGender(), account.getHeight(), account.getWeight()));
        Account account2 = server.loginAccount(account.getEmail(), account.getPassword());
        assertTrue(account2.getPassword().equals(account.getPassword()));
        assertTrue(account2.getGender().equals(account.getGender()));
        assertTrue(account2.getUsername().equals(account.getUsername()));
        assertEquals(1, server.getAccounts("un2").size());
    }

    @Test
    @Ignore
    public void testFansController() {
        assertTrue(server.followFan(1, 2));
        assertTrue(server.getFollowees(1).size() == 1);
        assertTrue(server.getFollowees(2).size() == 0);
        assertTrue(server.getFollowers(2).size() == 1);
        assertTrue(server.getFollowers(1).size() == 0);
        assertTrue(server.unfollowFan(1, 2));
        assertTrue(server.getFollowees(1).size() == 0);
        assertTrue(server.getFollowees(2).size() == 0);
        assertTrue(server.getFollowers(2).size() == 0);
        assertTrue(server.getFollowers(1).size() == 0);
    }

    @Test
    @Ignore
    public void testPortraitsController() {
        assertTrue(server.downloadPortrait(1) != null);
        saveFile("portrait", server.downloadPortrait(1));
        assertTrue(server.uploadPortrait(2333, new File("pic")));
    }

    @Test
    @Ignore
    public void testMomentsController() {
        assertTrue(server.postMoment(1, "code_test", null));
        assertTrue(server.getMoments(1).get(0).getMessage().equals("code_test"));
        assertTrue(server.getMoments(1).get(0).getAccountId() == 1);
        assertTrue(!server.getMoments(1).get(0).getHasPhoto());
        assertTrue(server.getMoments(1, 1).size() == 1);
        assertTrue(server.getMoments(1, 2).size() == 2);

        assertTrue(server.postMoment(1, "code_test", new File("pic")));
        assertTrue(server.getMoments(1).get(0).getMessage().equals("code_test"));
        assertTrue(server.getMoments(1).get(0).getAccountId() == 1);
        assertTrue(server.getMoments(1).get(0).getHasPhoto());
        assertTrue(server.getMoments(1, 1).size() == 1);
        assertTrue(server.getMoments(1, 2).size() == 2);
        saveFile("photo", server.downloadPhoto(server.getMoments(1, 1).get(0).getId()));
    }

    @Test
    @Ignore
    public void testRecordsController() {
        assertTrue(server.postRecord(1, 60, 111.11, 9.8, 99));
        assertTrue(server.getRecords(1).get(0).getDistance() == 111.11);
        assertTrue(server.getRecords(1).get(0).getAccountId() == 1);
        assertTrue(server.getRecords(1, 1).size() == 1);
        assertTrue(server.getRecords(1, 2).size() == 2);
    }

    @Test
    @Ignore
    public void testPhotosController() {
        assertTrue(server.postMoment(33, "ilovey", new File("pic")));
        assertTrue(server.downloadPhoto(1) != null);
        saveFile("photo", server.downloadPhoto(1));
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