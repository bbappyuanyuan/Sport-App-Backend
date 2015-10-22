package cn.edu.fudan.sport.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FansDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(Integer follower, Integer followee) {
        String sql = "INSERT INTO fans (follower, followee) VALUES (?, ?)";
        Object[] params = new Object[]{follower, followee};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer follower, Integer followee) {
        String sql = "DELETE FROM fans WHERE follower = ? AND followee = ?";
        Object[] params = new Object[]{follower, followee};
        jdbcTemplate.update(sql, params);
    }
}
