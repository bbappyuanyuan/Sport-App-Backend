package cn.edu.fudan.sport.domain;

import java.sql.Timestamp;

public class Moment {

    Integer id;
    Integer accountId;
    String message;
    Timestamp createD;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String gender) {
        this.message = message;
    }

    public Timestamp getCreateD() {
        return createD;
    }

    public void setCreateD(Timestamp createD) {
        this.createD = createD;
    }
}
