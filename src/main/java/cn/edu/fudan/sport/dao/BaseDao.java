package cn.edu.fudan.sport.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(String kk, String vv) {
        String sql = "INSERT INTO test (kk, vv) VALUES (?, ?)";
        int out = jdbcTemplate.update(sql, new Object[]{kk, vv});
        System.out.println(out);
    }
}
