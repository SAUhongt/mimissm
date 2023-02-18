package com.ht.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ht.mapper.ProductInfoMapper;
import com.ht.pojo.ProductInfo;
import com.ht.pojo.ProductInfoExample;
import com.ht.pojo.vo.ProductInfoVo;
import com.ht.service.ProductInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SRDZ
 * @date 2023/2/10 21:47
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

  @Autowired
  ProductInfoMapper productInfoMapper;

  @Override
  public List<ProductInfo> getAll() {
    return productInfoMapper.selectByExample(new ProductInfoExample());
  }

  @Override
  public PageInfo<ProductInfo> splitPage(int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    ProductInfoExample example = new ProductInfoExample();
    example.setOrderByClause("p_id desc");
    List list = productInfoMapper.selectByExample(example);
    PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }

  @Override
  public int save(ProductInfo info) {

    return productInfoMapper.insert(info);
  }

  @Override
  public ProductInfo one(int pid) {
    return productInfoMapper.selectByPrimaryKey(pid);
  }

  @Override
  public int update(ProductInfo info) {
    return productInfoMapper.updateByPrimaryKey(info);
  }

  @Override
  public int deleteById(int pid) {

    return productInfoMapper.deleteByPrimaryKey(pid);
  }

  @Override
  public int deletebatch(String[] ids) {
    return productInfoMapper.deletebatch(ids);
  }

  @Override
  public List<ProductInfo> selectCondition(ProductInfoVo vo) {
    return productInfoMapper.selectCondition(vo);
  }

  @Override
  public PageInfo<ProductInfo> splitPageCondition(int pageNum, int pageSize, ProductInfoVo vo) {
    PageHelper.startPage(pageNum, pageSize);
    List list = productInfoMapper.selectCondition(vo);
    PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
    return pageInfo;
  }
}
