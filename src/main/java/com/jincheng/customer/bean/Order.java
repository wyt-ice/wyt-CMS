package com.jincheng.customer.bean;

public class Order {
    private int orderId;
    private String proName;
    private String custName;
    private int counts;
    private int accountRecei;
    private int prepayment;
    private String address;
    private String orderState;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public int getAccountRecei() {
        return accountRecei;
    }

    public void setAccountRecei(int accountRecei) {
        this.accountRecei = accountRecei;
    }

    public int getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(int prepayment) {
        this.prepayment = prepayment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", proName='" + proName + '\'' +
                ", custName='" + custName + '\'' +
                ", counts=" + counts +
                ", accountRecei=" + accountRecei +
                ", prepayment=" + prepayment +
                ", address='" + address + '\'' +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}
