package cn.edu.fudan.sport.domain;

import java.sql.Timestamp;

public class Moment {

    Integer id;
    Integer accountId;
    String message;
    Boolean hasPhoto;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(Boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public Timestamp getCreateD() {
        return createD;
    }

    public void setCreateD(Timestamp createD) {
        this.createD = createD;
    }
}
