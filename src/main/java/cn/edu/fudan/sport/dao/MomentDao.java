package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Moment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class MomentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public synchronized Integer insert(Integer accountId, String message, Boolean hasPhoto, Timestamp createD) {
        String sql = "INSERT INTO moment (account_id, message, has_photo, create_d) VALUES (?, ?, ?, ?)";
        Object[] params = new Object[]{accountId, message, hasPhoto, createD};
        jdbcTemplate.update(sql, params);
        return jdbcTemplate.queryForObject("SELECT max(id) FROM moment", Integer.class);
    }

    public List<Moment> select(Integer accountId, Integer limit) {
        String sql = "SELECT * FROM moment WHERE account_id = ? ORDER BY ID DESC LIMIT ?";
        Object[] params = new Object[]{accountId, limit};
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Moment.class));
    }
}
