package cn.edu.fudan.sport.controller;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.view.AccountVo;
import cn.edu.fudan.sport.view.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseVo register(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "gender") String gender,
                           @RequestParam(value = "height") Double height,
                           @RequestParam(value = "weight") Double weight) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setGender(gender);
        account.setHeight(height);
        account.setWeight(weight);
        account.setCreateD(new Timestamp(new Date().getTime()));
        try {
            accountDao.insert(account);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public AccountVo login(@RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password) {
        Account account;
        try {
            account = accountDao.getAccount(email, password);
        } catch (Exception e) {
            return new AccountVo(0, null);
        }
        if (account == null)
            return new AccountVo(0, null);
        return new AccountVo(1, account);
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public BaseVo update(@PathVariable(value = "id") Integer id,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "gender") String gender,
                         @RequestParam(value = "height") Double height,
                         @RequestParam(value = "weight") Double weight) {
        Account account = new Account();
        account.setId(id);
        account.setPassword(password);
        account.setGender(gender);
        account.setHeight(height);
        account.setWeight(weight);
        try {
            accountDao.update(account);
        } catch (Exception e) {
            return new BaseVo(0);
        }
        return new BaseVo(1);
    }
}
