package com.jincheng.customer.dao;

import com.jincheng.customer.bean.Customer;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
//@Repository//解决service无法装配dao中mapper
public interface CustomerMapper {
    List<Customer> queryCustomer(Customer customer, int start, int length);
    List<Customer> test();

    int queryTotalCount(Customer customer);

    void addCustomer(Customer customer);

    void delCustomer(Integer custId);

    void modifyCustomer(Customer customer);
//    List<Customer> fuzzyQuery(String index);
}
