package com.liyk.AOPAuth.framework.common.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liyk
 * @date 2018-04-15 上午 10:20
 */
public class TableResult<T> implements Serializable{
    private long total = 0;
    private List<T> rows = new ArrayList<>();

    public TableResult() {
    }

    public TableResult(long total, List<T> data) {
        this.total = total;
        this.rows = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
