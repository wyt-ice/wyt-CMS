package com.jincheng.customer.service;

import com.jincheng.customer.bean.Product;
import com.jincheng.customer.dao.productMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class productService {
    @Autowired
    private productMapper productMapper;

    public List<Product> queryPro(Integer proId,String proName,String proType){
        return productMapper.queryPro(proId,proName,proType);
    }

    public void addProduct(Product product){
        productMapper.addProduct(product);
    }

    public void delProduct(int proId){
        productMapper.delProduct(proId);
    }

    public void modifyProduct(Product product){
        productMapper.modifyProduct(product);
    }

    public Product queryProForOrder(String proName){
        return productMapper.queryProForOrder(proName);
    }

    public String proImg(Product product){
        String base64String = product.getProImg().split(",")[1];
        UUID UUId=UUID.randomUUID();
        String proImg =UUId.toString()+".png";
        String filePathName = "image/product/"+proImg;
        System.out.println(filePathName);
        try {
            // Base64解码
            byte[] bytes = Base64.decodeBase64(base64String);
//            System.out.println(Arrays.toString(bytes));
            // 生成jpeg图片
            FileUtils.writeByteArrayToFile(new File(filePathName),bytes);
        } catch (Exception e) {
            System.out.println(e);

        }
        return proImg;
    }
}
