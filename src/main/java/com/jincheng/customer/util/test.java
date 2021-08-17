package com.jincheng.customer.util;

import com.jincheng.customer.bean.Customer;
import com.jincheng.customer.bean.Order;
import com.jincheng.customer.dao.CustomerMapper;
import com.jincheng.customer.dao.OrderMapper;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class test {
    @Autowired
    private OrderMapper orderMapper;

//    public ResponseEntity write() throws BiffException, IOException, WriteException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
//        String fileName = "order-" + (dateFormat.format(new Date())) + ".xls";
//        File file = new File(fileName);
//        for (int i = 0; i < 2; i++) {
//            List<Order> orders = orderMapper.queryOrder("");
//            System.out.println(orders);
//            String[][] contents = new String[2][];
//            for (int j = 0; j < orders.size(); j++) {
//                contents[j] = orders.get(j).toString().split(",");
//            }
//            //写入xls
//            Download.write(new String[]{"orderId", "custName", "proName", "counts", "accountRecei", "prepayment","address","orderState"}, fileName, contents);
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", fileName);
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        try {
//            return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
//        } catch (Exception E) {
//            return new ResponseEntity(E.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
//        }
//    }

}
