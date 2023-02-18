package com.ht.service.impl;

import com.ht.mapper.ProductTypeMapper;
import com.ht.pojo.ProductType;
import com.ht.pojo.ProductTypeExample;
import com.ht.service.ProductTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SRDZ
 * @date 2023/2/11 17:35
 */

@Service("ProductTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {

  @Autowired
  ProductTypeMapper productTypeMapper;

  @Override
  public List<ProductType> getAll() {
    return productTypeMapper.selectByExample(new ProductTypeExample());
  }
}
