package com.jincheng.customer.contoller;


import com.jincheng.customer.bean.Customer;
import com.jincheng.customer.bean.Order;
import com.jincheng.customer.bean.Product;
import com.jincheng.customer.service.productService;
import com.jincheng.customer.util.Download;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class productController {
    @Autowired
    productService productService;
    @PostMapping("/query")
    @ResponseBody
    public List<Product> queryPro(Integer proId,String proName,String proType){
        List<Product> list = productService.queryPro(proId,proName,proType);
        System.out.println(list);
        return list;
    }

    @PostMapping
    @ResponseBody
    public void addCustomer(@RequestBody Product product) {
//        System.out.println(product);
        String proImg = productService.proImg(product);
        product.setProImg(proImg);
        //排序
        System.out.println(product);
//        productService.addProduct(product);
    }

    @DeleteMapping
    @ResponseBody
    public void delProduct(Integer id) {
//        System.out.println(id);
       productService.delProduct(id);
    }
    @PutMapping
    @ResponseBody
    public void modifyProduct(@RequestBody Product product) {
        System.out.println(product);
        String proImg = productService.proImg(product);
        product.setProImg(proImg);
        productService.modifyProduct(product);
    }


}
