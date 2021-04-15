package com.zqy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zqy.mapper.CategoryMapper;
import com.zqy.mapper.ProductMapper;
import com.zqy.pojo.Product;
import com.zqy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ProductServiceImpl
 * @Description TODO
 * @Author: zqy
 * @Date: 2021/4/13 15:20
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List getCategory() {
        return categoryMapper.selectAll();
    }

    @Override
    public PageInfo getPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List page = productMapper.getPage();
        return new PageInfo(page);
    }

    @Override
    public Integer remove(Integer pid) {
        Product product = new Product();
        product.setPid(pid);
        product.setFlag("1");
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product getOne(Integer pid) {
        return productMapper.getOne(pid);
    }

    @Override
    public Integer addProduct(Product product) {
        product.setFlag("0");
        return productMapper.insertSelective(product);
    }

    @Override
    public Integer updateProduct(Product product) {
        return productMapper.updateByPrimaryKeySelective(product);
    }
}
