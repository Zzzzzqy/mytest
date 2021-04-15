package com.zqy.mapper;

import com.zqy.pojo.Product;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ProductMapper extends Mapper<Product> {
    List getPage();

    Product getOne(Integer pid);
}
