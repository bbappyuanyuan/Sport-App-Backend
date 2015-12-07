package cn.edu.fudan.sport.vo;

import cn.edu.fudan.sport.domain.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountsVo extends BaseVo {

    List<Account> accounts = new ArrayList<>();

    public AccountsVo() {
        super();
    }

    public AccountsVo(int status, List<Account> accounts) {
        super(status);
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
