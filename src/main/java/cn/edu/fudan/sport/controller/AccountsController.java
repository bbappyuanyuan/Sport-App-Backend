package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.vo.AccountVo;
import cn.edu.fudan.sport.vo.AccountsVo;
import cn.edu.fudan.sport.vo.BaseVo;
import cn.edu.fudan.sport.vo.IdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(method = RequestMethod.POST)
    public IdVo register(@RequestParam String email, @RequestParam String password, @RequestParam String username,
                         @RequestParam String gender, @RequestParam Double height, @RequestParam Double weight) {
        Integer respId = null;
        try {
            accountDao.insert(email, password, username, gender, height, weight, new Timestamp(new Date().getTime()));
        } catch (Exception e) {
            return new IdVo(0, null);
        }
        return new IdVo(1, respId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AccountVo get(@PathVariable Integer id) {
        Account account;
        try {
            account = accountDao.select(id);
        } catch (Exception e) {
            return new AccountVo(0, null);
        }
        if (account == null)
            return new AccountVo(0, null);
        return new AccountVo(1, account);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"email", "password"})
    public AccountVo login(@RequestParam String email, @RequestParam String password) {
        Account account;
        try {
            account = accountDao.select(email, password);
        } catch (Exception e) {
            return new AccountVo(0, null);
        }
        if (account == null)
            return new AccountVo(0, null);
        return new AccountVo(1, account);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"username"})
    public AccountsVo get(@RequestParam String username) {
        List<Account> accounts;
        try {
            accounts = accountDao.select(username);
        } catch (Exception e) {
            return new AccountsVo(0, null);
        }
        if (accounts == null)
            return new AccountsVo(0, null);
        return new AccountsVo(1, accounts);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseVo update(@PathVariable Integer id, @RequestParam String password, @RequestParam String username,
                         @RequestParam String gender, @RequestParam Double height, @RequestParam Double weight) {
        try {
            accountDao.update(id, password, username, gender, height, weight);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }
}
