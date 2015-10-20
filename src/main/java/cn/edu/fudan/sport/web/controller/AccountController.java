package cn.edu.fudan.sport.web.controller;

import cn.edu.fudan.sport.dao.AccountDao;
import cn.edu.fudan.sport.domain.Account;
import cn.edu.fudan.sport.web.view.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
