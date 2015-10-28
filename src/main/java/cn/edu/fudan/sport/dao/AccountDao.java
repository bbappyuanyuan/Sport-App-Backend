package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Account account) {
        String sql = "INSERT INTO account (email, password, create_d, gender, height, weight)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{account.getEmail(), account.getPassword(), account.getCreateD(),
                account.getGender(), account.getHeight(), account.getWeight()};
        jdbcTemplate.update(sql, params);
    }

    public Account get(String email, String password) {
        String sql = "SELECT * FROM account WHERE email = ? AND password = ?";
        Object[] params = new Object[]{email, password};
        return (Account) jdbcTemplate.queryForObject(sql, params, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPassword(rs.getString("password"));
                account.setGender(rs.getString("gender"));
                account.setHeight(rs.getDouble("height"));
                account.setWeight(rs.getDouble("weight"));
                account.setCreateD(rs.getTimestamp("create_d"));
                return account;
            }
        });
    }

    public Integer getAccountId(String email) {
        String sql = "SELECT id FROM account WHERE email = ?";
        Object[] params = new Object[]{email};
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    public void update(Account account) {
        String sql = "UPDATE account\n" +
                "SET password = ?, gender = ?, height = ?, weight = ?\n" +
                "WHERE id = ?";
        Object[] params = new Object[]{account.getPassword(), account.getGender(), account.getHeight(),
                account.getWeight(), account.getId()};
        jdbcTemplate.update(sql, params);
    }
}
