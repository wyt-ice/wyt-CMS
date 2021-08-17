package com.jincheng.customer.dao;

import com.jincheng.customer.bean.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface productMapper {
    List<Product> queryPro(Integer proId,String proName,String proType);
    void addProduct(Product product);
    void delProduct(Integer proId);
    void modifyProduct(Product product);
    Product queryProForOrder(String proName);
}
