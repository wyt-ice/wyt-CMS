package com.jincheng.customer.contoller;

import com.jincheng.customer.bean.Customer;
import com.jincheng.customer.bean.DataTablesOutput;
import com.jincheng.customer.service.CustomerService;
import com.jincheng.customer.service.DownloadService;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/customer")//前端的请求通过这个注解做映射
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DownloadService downloadService;

    @PostMapping("/count")
    @ResponseBody //返回json数据到前端
    public int queryTotalCount(@RequestBody Customer customer) {
        return customerService.queryTotalCount(customer);
    }
    
    @PostMapping("/query")
    @ResponseBody //返回json数据到前端
    public DataTablesOutput getCustomer(Customer customer, Integer start, Integer length, Integer draw, Integer recordsTotal) {
        return customerService.queryCustomer(customer,start,length,draw,recordsTotal);
    }
//    @GetMapping("/test")
//    @ResponseBody //返回json数据到前端
//    public List<Customer> test() {
//        return customerService.test();
//    }
    @PostMapping
    @ResponseBody
    public void addCustomer(@RequestBody Customer customer) {
        //排序
//        System.out.println(customer);
        customerService.addCustomer(customer);
    }

    @DeleteMapping
    @ResponseBody
    public void delCustomer(Integer id) {
//        System.out.println(id);
        customerService.delCustomer(id);
    }

    @PutMapping
    @ResponseBody
    public void modifyCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        customerService.modifyCustomer(customer);
    }
    @GetMapping("/download")
    @ResponseBody
    public void download(String downloadType) throws BiffException, IOException, WriteException {
        downloadService.write(downloadType);
    }

}
