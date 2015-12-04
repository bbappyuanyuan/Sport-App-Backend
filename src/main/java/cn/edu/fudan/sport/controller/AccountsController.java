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
@RequestMapping("/accounts")
public class AccountsController {

    @Autowired
    private AccountDao accountDao;

    @RequestMapping(method = RequestMethod.POST)
    public BaseVo register(@RequestParam String email, @RequestParam String password,
                           @RequestParam String gender, @RequestParam Double height, @RequestParam Double weight) {
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

    @RequestMapping(method = RequestMethod.GET)
    public AccountVo login(@RequestParam String email, @RequestParam String password) {
        Account account;
        try {
            account = accountDao.get(email, password);
        } catch (Exception e) {
            return new AccountVo(0, null);
        }
        if (account == null)
            return new AccountVo(0, null);
        return new AccountVo(1, account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BaseVo update(@PathVariable Integer id, @RequestParam String password,
                         @RequestParam String gender, @RequestParam Double height, @RequestParam Double weight) {
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
