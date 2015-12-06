package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.dao.FanDao;
import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.view.AccountsVo;
import cn.edu.fudan.sport.view.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fans/{id}")
public class FansController {

    @Autowired
    private FanDao fanDao;
    @Autowired
    private AccountDao accountDao;

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
    public AccountsVo getFollowees(@PathVariable Integer id) {
        List<Account> accounts = null;
        try {
            List<Integer> ids = fanDao.selectFollowees(id);
            accounts = new ArrayList<>();
            for (Integer account_id : ids) {
                Account account = accountDao.select(account_id);
                if (account == null)
                    return new AccountsVo(0, null);
                accounts.add(account);
            }
        } catch (Exception e) {
            return new AccountsVo(0, null);
        }
        return new AccountsVo(1, accounts);
    }

    @RequestMapping(value = "/followers", method = RequestMethod.GET)
    public AccountsVo getFollowers(@PathVariable Integer id) {
        List<Account> accounts = null;
        try {
            List<Integer> ids = fanDao.selectFollowees(id);
            accounts = new ArrayList<>();
            for (Integer account_id : ids) {
                Account account = accountDao.select(account_id);
                if (account == null)
                    return new AccountsVo(0, null);
                accounts.add(account);
            }
        } catch (Exception e) {
            return new AccountsVo(0, null);
        }
        return new AccountsVo(1, accounts);
    }
}
