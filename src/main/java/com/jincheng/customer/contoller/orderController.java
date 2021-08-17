package com.jincheng.customer.contoller;

import com.jincheng.customer.bean.Order;
import com.jincheng.customer.service.DownloadService;
import com.jincheng.customer.service.orderService;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/order")
public class orderController {
    @Autowired
    private orderService orderService;
    @Autowired
    private DownloadService downloadService;

    @PostMapping("/query")
    @ResponseBody
    public List<Order> queryOrder(String orderState){
        List<Order> list =orderService.queryOrder(orderState);
        return list;
    }
    @PostMapping
    @ResponseBody
    public void addCustomer(@RequestBody Order order) {
        orderService.addOrder(order);
    }
    @DeleteMapping
    @ResponseBody
    public void delProduct(Integer id) {
//        System.out.println(id);
       orderService.delOrder(id);
    }
    @PutMapping
    @ResponseBody
    public void modifyProduct(@RequestBody Order order) {
        System.out.println(order);
        orderService.modifyOrder(order);
    }

    @GetMapping("/download")
    @ResponseBody
    public void write(String downloadType) throws BiffException, IOException, WriteException {
        downloadService.write(downloadType);
    }
}
