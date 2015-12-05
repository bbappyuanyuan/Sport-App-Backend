package cn.edu.fudan.sport.view;

import java.util.List;

public class FansVo extends BaseVo {

    List<Integer> fans;

    public FansVo(int status, List<Integer> fans) {
        super(status);
        this.fans = fans;
    }

    public List<Integer> getFans() {
        return fans;
    }

    public void setFans(List<Integer> fans) {
        this.fans = fans;
    }
}
