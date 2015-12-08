package cn.edu.fudan.sport.vo;

public class IdVo extends BaseVo {

    Integer id;

    public IdVo() {
        super();
    }

    public IdVo(int status, Integer id) {
        super(status);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
