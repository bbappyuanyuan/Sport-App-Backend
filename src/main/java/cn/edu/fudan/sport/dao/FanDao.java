package cn.edu.fudan.sport.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FanDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(Integer follower, Integer followee) {
        String sql = "INSERT INTO fan (follower, followee) VALUES (?, ?)";
        Object[] params = new Object[]{follower, followee};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer follower, Integer followee) {
        String sql = "DELETE FROM fan WHERE follower = ? AND followee = ?";
        Object[] params = new Object[]{follower, followee};
        jdbcTemplate.update(sql, params);
    }

    public List<Integer> get(Integer follower) {
        String sql = "SELECT * FROM fan WHERE follower = ?";
        Object[] params = new Object[]{follower};
        return jdbcTemplate.queryForList(sql, params, Integer.class);
    }
}
