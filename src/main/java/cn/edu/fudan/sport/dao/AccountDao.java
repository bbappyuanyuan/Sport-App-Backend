package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Account account) {
        String sql = "INSERT INTO account (email, password, create_d, gender, height, weight)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("ut = " + jdbcTemplate.update(sql, account.getEmail(),
                account.getPassword(), account.getCreateD(), account.getGender(),
                account.getHeight(), account.getWeight()));
    }
}
