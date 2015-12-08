package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public synchronized Integer insert(String email, String password, String username, String gender, Double height, Double weight,
                          Timestamp createD) {
        String sql = "INSERT INTO account (email, password, username, create_d, gender, height, weight)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{email, password, username, createD, gender, height, weight};
        jdbcTemplate.update(sql, params);
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public Account select(String email, String password) {
        String sql = "SELECT * FROM account WHERE email = ? AND password = ?";
        Object[] params = new Object[]{email, password};
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Account.class));
    }

    public Account select(Integer id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        Object[] params = new Object[]{id};
        return jdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<>(Account.class));
    }

    public List<Account> select(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        Object[] params = new Object[]{username};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Account.class));
    }

    public void update(Integer id, String password, String username, String gender, Double height, Double weight) {
        String sql = "UPDATE account\n" +
                "SET password = ?, username = ?, gender = ?, height = ?, weight = ?\n" +
                "WHERE id = ?";
        Object[] params = new Object[]{password, username, gender, height, weight, id};
        jdbcTemplate.update(sql, params);
    }
}
