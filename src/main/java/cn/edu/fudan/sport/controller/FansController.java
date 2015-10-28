package cn.edu.fudan.sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.dao.FansDao;
import cn.edu.fudan.sport.view.BaseVo;

@RestController
@RequestMapping("accounts/{id}/fans")
public class FansController {

    @Autowired AccountDao accountDao;
    @Autowired FansDao fansDao;

    @RequestMapping(value = "/{id2}", method = RequestMethod.POST)
    public BaseVo follow(@PathVariable Integer id, @PathVariable Integer id2) {
        try {
            fansDao.insert(id, id2);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/{id2}", method = RequestMethod.DELETE)
    public BaseVo unfollow(@PathVariable Integer id, @PathVariable Integer id2) {
        try {
            fansDao.delete(id, id2);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }
}
