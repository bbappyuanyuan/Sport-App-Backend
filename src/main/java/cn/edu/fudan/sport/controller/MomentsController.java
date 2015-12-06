package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.FanDao;
import cn.edu.fudan.sport.dao.MomentDao;
import cn.edu.fudan.sport.domain.Moment;
import cn.edu.fudan.sport.view.BaseVo;
import cn.edu.fudan.sport.view.MomentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/moments/{id}")
public class MomentsController {

    @Autowired
    private MomentDao momentDao;
    @Autowired
    private FanDao fanDao;

    @RequestMapping(method = RequestMethod.POST)
    public BaseVo post(@PathVariable Integer id, @RequestParam String message) {
        try {
            momentDao.insert(id, message, new Timestamp(new Date().getTime()));
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(method = RequestMethod.GET)
    public MomentsVo get(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "10") Integer limit) {
        List<Moment> moments = null;
        try {
            List<Integer> followees = fanDao.selectFollowees(id);
            moments = momentDao.select(id, limit);
            for (Integer followee : followees)
                moments.addAll(momentDao.select(followee, limit));
            if (moments.size() > limit)
                moments = moments.subList(0, limit);
        } catch (Exception e) {
            return new MomentsVo(0, null);
        }
        if (moments == null)
            return new MomentsVo(0, null);
        return new MomentsVo(1, moments);
    }
}
