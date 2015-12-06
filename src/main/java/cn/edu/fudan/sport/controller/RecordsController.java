package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.RecordDao;
import cn.edu.fudan.sport.domain.Record;
import cn.edu.fudan.sport.view.BaseVo;
import cn.edu.fudan.sport.view.RecordsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/records/{id}")
public class RecordsController {

    @Autowired
    private RecordDao recordDao;

    @RequestMapping(method = RequestMethod.POST)
    public BaseVo record(@PathVariable Integer id, @RequestParam Integer duration, @RequestParam Double distance,
                         @RequestParam Double maxSpeed, @RequestParam Integer steps) {
        try {
            recordDao.insert(new Timestamp(new Date().getTime()), id, duration, distance, maxSpeed, steps);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public RecordsVo get(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "100") Integer limit) {
        List<Record> records = null;
        try {
            records = recordDao.select(id, limit);
        } catch (Exception e) {
            return new RecordsVo(0, null);
        }
        if (records == null)
            return new RecordsVo(0, null);
        return new RecordsVo(1, records);
    }
}
