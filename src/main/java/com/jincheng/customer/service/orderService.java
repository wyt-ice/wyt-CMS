package com.jincheng.customer.service;

import com.jincheng.customer.bean.Order;
import com.jincheng.customer.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {
    @Autowired
    private OrderMapper orderMapper;
    public List<Order> queryOrder(String orderState){
        return orderMapper.queryOrder(orderState);
    }
    public Order queryOrderById(Integer orderId){
        return orderMapper.queryOrderById(orderId);
    }
    public void addOrder(Order order){
        orderMapper.addOrder(order);
    }
    public void delOrder(Integer orderId){
        orderMapper.delOrder(orderId);
    }
    public void modifyOrder(Order order){
        orderMapper.modifyOrder(order);
    }

}
