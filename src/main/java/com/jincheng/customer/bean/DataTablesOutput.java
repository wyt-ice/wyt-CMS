package com.jincheng.customer.bean;

import java.util.List;

public class DataTablesOutput {
    private int draw=1; // 第几次请求
    private int recordsTotal = 1000;// 起止位置
    private int recordsFiltered = 1000; // 数据长度
    List<Customer> data;

    public DataTablesOutput(int draw, int recordsTotal, int recordsFiltered, List<Customer> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }
    public DataTablesOutput(int draw, List<Customer> data) {
        this.draw = draw;

        this.data = data;
    }

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> customers) {
        this.data = customers;
    }

    public DataTablesOutput() {

    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
