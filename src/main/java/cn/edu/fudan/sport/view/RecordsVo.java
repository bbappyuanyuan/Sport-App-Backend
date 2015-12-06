package cn.edu.fudan.sport.view;

import cn.edu.fudan.sport.domain.Record;

import java.util.List;

public class RecordsVo extends BaseVo {

    List<Record> records;

    public RecordsVo(int status, List<Record> records) {
        super(status);
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
