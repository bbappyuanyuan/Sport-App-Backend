package cn.edu.fudan.sport.domain;

import java.sql.Timestamp;

public class Account {

    Integer id;
    String email;
    String password;
    String gender;
    Double height;
    Double weight;
    Timestamp createD;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateD() {
        return createD;
    }

    public void setCreateD(Timestamp createD) {
        this.createD = createD;
    }
}
