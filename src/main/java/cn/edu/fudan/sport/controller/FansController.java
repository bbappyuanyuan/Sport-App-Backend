package cn.edu.fudan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.dao.FansDao;
import cn.edu.fudan.sport.view.BaseVo;

@RestController @RequestMapping("/fans") public class FansController {

    @Autowired AccountDao accountDao;
    @Autowired FansDao fansDao;

    @RequestMapping(value = "/{id}/follow", method = RequestMethod.POST)
    public BaseVo follow(@PathVariable Integer id, @RequestParam Integer followeeId) {
        try {
            fansDao.insert(id, followeeId);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/{id}/unfollow", method = RequestMethod.POST)
    public BaseVo unfollow(@PathVariable Integer id, @RequestParam Integer followeeId) {
        try {
            fansDao.delete(id, followeeId);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }
}
