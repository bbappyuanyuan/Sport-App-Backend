package cn.edu.fudan.sport.web.controller;

import cn.edu.fudan.sport.web.view.BaseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseVo login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        System.out.println("getin login");
        System.out.println(email + "===" + password);
        return new BaseVo(email, password);
    }
}
