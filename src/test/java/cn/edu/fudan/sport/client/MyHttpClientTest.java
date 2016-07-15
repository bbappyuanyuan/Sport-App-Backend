package cn.edu.fudan.sport.client;

import cn.edu.fudan.sport.domain.Account;
import org.junit.Ignore;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyHttpClientTest {

    MyHttpClient client = new MyHttpClient();

    @Test
    @Ignore
    public void testAccountsController() {
        assertTrue(client.registerAccount("code_test@code_test.com", "123456", "un", "male", 175.0, 60.0));
        Account account = client.loginAccount("code_test@code_test.com", "123456");
        Account account1 = client.getAccount(account.getId());
        assertTrue(account1.getEmail().equals(account.getEmail()));
        assertTrue(account.getEmail().equals("code_test@code_test.com"));
        assertTrue(account.getHeight().equals(175.0));
        account.setPassword("new");
        account.setUsername("un2");
        account.setGender("female");
        assertTrue(client.updateAccount(account.getId(), account.getPassword(), account.getUsername(), account.getGender(), account.getHeight(), account.getWeight()));
        Account account2 = client.loginAccount(account.getEmail(), account.getPassword());
        assertTrue(account2.getPassword().equals(account.getPassword()));
        assertTrue(account2.getGender().equals(account.getGender()));
        assertTrue(account2.getUsername().equals(account.getUsername()));
        assertEquals(1, client.getAccounts("un2").size());
    }

    @Test
    @Ignore
    public void testFansController() {
        assertTrue(client.followFan(1, 2));
        assertTrue(client.getFollowees(1).size() == 1);
        assertTrue(client.getFollowees(2).size() == 0);
        assertTrue(client.getFollowers(2).size() == 1);
        assertTrue(client.getFollowers(1).size() == 0);
        assertTrue(client.unfollowFan(1, 2));
        assertTrue(client.getFollowees(1).size() == 0);
        assertTrue(client.getFollowees(2).size() == 0);
        assertTrue(client.getFollowers(2).size() == 0);
        assertTrue(client.getFollowers(1).size() == 0);
    }

    @Test
    @Ignore
    public void testPortraitsController() {
        assertTrue(client.downloadPortrait(1) != null);
        saveFile("portrait", client.downloadPortrait(1));
        assertTrue(client.uploadPortrait(2333, new File("pic")));
    }

    @Test
    @Ignore
    public void testMomentsController() {
        assertTrue(client.postMoment(1, "code_test", null));
        assertTrue(client.getMoments(1).get(0).getMessage().equals("code_test"));
        assertTrue(client.getMoments(1).get(0).getAccountId() == 1);
        assertTrue(!client.getMoments(1).get(0).getHasPhoto());
        assertTrue(client.getMoments(1, 1).size() == 1);
        assertTrue(client.getMoments(1, 2).size() == 2);

        assertTrue(client.postMoment(1, "code_test", new File("pic")));
        assertTrue(client.getMoments(1).get(0).getMessage().equals("code_test"));
        assertTrue(client.getMoments(1).get(0).getAccountId() == 1);
        assertTrue(client.getMoments(1).get(0).getHasPhoto());
        assertTrue(client.getMoments(1, 1).size() == 1);
        assertTrue(client.getMoments(1, 2).size() == 2);
        saveFile("photo", client.downloadPhoto(client.getMoments(1, 1).get(0).getId()));
    }

    @Test
    @Ignore
    public void testRecordsController() {
        assertTrue(client.postRecord(1, 60, 111.11, 9.8, 99));
        assertTrue(client.getRecords(1).get(0).getDistance() == 111.11);
        assertTrue(client.getRecords(1).get(0).getAccountId() == 1);
        assertTrue(client.getRecords(1, 1).size() == 1);
        assertTrue(client.getRecords(1, 2).size() == 2);
    }

    @Test
    @Ignore
    public void testPhotosController() {
        assertTrue(client.postMoment(33, "ilovey", new File("pic")));
        assertTrue(client.downloadPhoto(1) != null);
        saveFile("photo", client.downloadPhoto(1));
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