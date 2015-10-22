package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.dao.FansDao;
import cn.edu.fudan.sport.view.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fans")
public class FansController {

    @Autowired
    AccountDao accountDao;
    @Autowired
    FansDao fansDao;

    @RequestMapping(value = "/{id}/follow", method = RequestMethod.POST)
    public BaseVo follow(@PathVariable(value = "id") Integer id,
                         @RequestParam(value = "followeeId") Integer followeeId) {
        try {
            fansDao.insert(id, followeeId);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/{id}/unfollow", method = RequestMethod.POST)
    public BaseVo unfollow(@PathVariable(value = "id") Integer id,
                           @RequestParam(value = "followeeId") Integer followeeId) {
        try {
            fansDao.delete(id, followeeId);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }
}
