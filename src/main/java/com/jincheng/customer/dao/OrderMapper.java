package com.jincheng.customer.dao;

import com.jincheng.customer.bean.Order;
import com.jincheng.customer.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OrderMapper {
    List<Order> queryOrder(String orderState);
    Order queryOrderById(Integer orderId);
    void addOrder(Order order);
    void delOrder(Integer orderId);
    void modifyOrder(Order order);
}
