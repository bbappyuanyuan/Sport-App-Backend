package cn.edu.fudan.sport.view;

import cn.edu.fudan.sport.domain.Moment;

import java.util.List;

public class MomentsVo extends BaseVo {

    List<Moment> moments;

    public MomentsVo(int status, List<Moment> moments) {
        super(status);
        this.moments = moments;
    }

    public List<Moment> getMoments() {
        return moments;
    }

    public void setMoments(List<Moment> moments) {
        this.moments = moments;
    }
}
