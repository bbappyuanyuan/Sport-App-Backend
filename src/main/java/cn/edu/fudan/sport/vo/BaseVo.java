package cn.edu.fudan.sport.vo;

public class BaseVo {

    int status;

    public BaseVo() {
    }

    public BaseVo(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
