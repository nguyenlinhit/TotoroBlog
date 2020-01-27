package com.totoro.blog.framework.web.page;

import java.io.Serializable;
import java.util.List;

/**
 * @version 1.0
 * @className: TableDataInfo
 * @description: Table paging data object
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class TableDataInfo implements Serializable {
    /*Total*/
    private long total;
    /*List data*/
    private List<?> rows;
    /*Message status code*/
    private int code;
    /*Message content*/
    private int msg;

    /**
     * Tabular data object
     */
    public TableDataInfo() {
    }

    /**
     * Pagination
     *
     * @param total Total
     * @param rows List data
     */
    public TableDataInfo(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
