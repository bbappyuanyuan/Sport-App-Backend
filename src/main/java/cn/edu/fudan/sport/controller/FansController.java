package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.FanDao;
import cn.edu.fudan.sport.view.BaseVo;
import cn.edu.fudan.sport.view.FansVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fans/{id}")
public class FansController {

    @Autowired
    private FanDao fanDao;

    @RequestMapping(value = "/followees/{id2}", method = RequestMethod.POST)
    public BaseVo follow(@PathVariable Integer id, @PathVariable Integer id2) {
        try {
            fanDao.insert(id, id2);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/followees/{id2}", method = RequestMethod.DELETE)
    public BaseVo unfollow(@PathVariable Integer id, @PathVariable Integer id2) {
        try {
            fanDao.delete(id, id2);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/followees", method = RequestMethod.GET)
    public FansVo getFollowees(@PathVariable Integer id) {
        List<Integer> fans;
        try {
            fans = fanDao.selectFollowees(id);
        } catch (Exception e) {
            return new FansVo(0, null);
        }
        return new FansVo(1, fans);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public FansVo getFollowers(@PathVariable Integer id) {
        List<Integer> fans;
        try {
            fans = fanDao.selectFollowers(id);
        } catch (Exception e) {
            return new FansVo(0, null);
        }
        return new FansVo(1, fans);
    }
}
