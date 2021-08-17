package com.jincheng.customer.service;

import com.jincheng.customer.bean.Customer;
import com.jincheng.customer.bean.DataTablesOutput;
import com.jincheng.customer.dao.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    public DataTablesOutput queryCustomer(Customer customer, Integer start , Integer length, Integer draw, Integer recordsTotal){

        List<Customer> customers = customerMapper.queryCustomer(customer,start,length);
        return new DataTablesOutput(draw,recordsTotal,recordsTotal,customers);
    }
    public int queryTotalCount(Customer customer){
        //PageHelper.startPage(2,100);
        return customerMapper.queryTotalCount(customer);

    }

    public void addCustomer(Customer customer) {
        customerMapper.addCustomer(customer);
    }

    public void delCustomer(int custId) {
        customerMapper.delCustomer(custId);
    }

    public void modifyCustomer(Customer customer) {
        customerMapper.modifyCustomer(customer);
    }
    public List<Customer> test(){
        List<Customer> list = customerMapper.test();
        return list;
    }
}
