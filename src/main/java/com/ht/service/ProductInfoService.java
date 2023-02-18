package com.ht.service;

import com.github.pagehelper.PageInfo;
import com.ht.mapper.ProductInfoMapper;
import com.ht.pojo.ProductInfo;
import com.ht.pojo.vo.ProductInfoVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SRDZ
 * @date 2023/2/10 21:45
 */
public interface ProductInfoService {

  List<ProductInfo> getAll();

  PageInfo<ProductInfo> splitPage(int pageNum,int pageSize);

  int save(ProductInfo info);

  ProductInfo one(int pid);

  int update(ProductInfo info);

  int deleteById(int pid);

  int deletebatch(String[] ids);

  List<ProductInfo> selectCondition(ProductInfoVo vo);

  PageInfo<ProductInfo> splitPageCondition(int pageNum,int pageSize,ProductInfoVo vo);

}
