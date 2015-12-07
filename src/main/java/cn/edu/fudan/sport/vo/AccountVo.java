package cn.edu.fudan.sport.vo;

import cn.edu.fudan.sport.domain.Account;

public class AccountVo extends BaseVo {

    Account account;

    public AccountVo() {
        super();
    }

    public AccountVo(int status, Account account) {
        super(status);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
