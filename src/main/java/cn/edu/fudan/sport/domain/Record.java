package cn.edu.fudan.sport.domain;

import java.sql.Timestamp;

public class Record {

    Integer id;
    Integer accountId;
    Integer duration;
    Double distance;
    Double maxSpeed;
    Integer steps;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Timestamp getCreateD() {
        return createD;
    }

    public void setCreateD(Timestamp createD) {
        this.createD = createD;
    }
}
