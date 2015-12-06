package cn.edu.fudan.sport.dao;

import cn.edu.fudan.sport.domain.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class RecordDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(Timestamp createD, Integer accountId, Integer duration, Double distance, Double maxSpeed,
                       Integer steps) {
        String sql = "INSERT INTO record (create_d, account_id, duration, distance, max_speed, steps)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[]{createD, accountId, duration, distance, maxSpeed, steps};
        jdbcTemplate.update(sql, params);
    }

    public List<Record> select(Integer accountId, Integer limit) {
        String sql = "SELECT * FROM record WHERE account_id = ? ORDER BY ID DESC LIMIT ?";
        Object[] params = new Object[]{accountId, limit};
        return jdbcTemplate.queryForList(sql, params, Record.class);
    }
}
