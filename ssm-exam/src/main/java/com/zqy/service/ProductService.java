package com.zqy.service;

import com.github.pagehelper.PageInfo;
import com.zqy.pojo.Product;

import java.util.List;

/**
 * @ClassName: ProductService
 * @Description TODO
 * @Author: zqy
 * @Date: 2021/4/13 15:20
 * @Version 1.0
 */
public interface ProductService {
    List getCategory();
    PageInfo getPage(Integer pageNum,Integer pageSize);

    Integer remove(Integer pid);

    Product getOne(Integer pid);

    Integer addProduct(Product product);

    Integer updateProduct(Product product);
}
