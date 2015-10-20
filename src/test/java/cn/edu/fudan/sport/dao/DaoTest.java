package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-spring.xml")
public class DaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testInsert() {
        Account account = new Account();
        account.setEmail("test2@fudan.edu.cn");
        account.setPassword("test1@fudan.edu.cn");
        account.setCreateD(new Timestamp(new Date().getTime()));
        String sql = "INSERT INTO account (email, password, create_d) VALUES (?, ?, ?)";
        try {
            System.out.println(jdbcTemplate.update(sql, account.getEmail(), account.getPassword(), account.getCreateD()));
        } catch (Exception e) {
        }
        System.out.println("yyyyyyy");
    }
}