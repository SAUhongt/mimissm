package com.ht.service.impl;

import com.ht.mapper.AdminMapper;
import com.ht.pojo.Admin;
import com.ht.pojo.AdminExample;
import com.ht.service.AdminService;
import com.ht.utils.MD5Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SRDZ
 * @date 2023/2/9 18:19
 */
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private AdminMapper adminMapper;

  @Override
  public Admin login(String name, String pwd) {
    AdminExample adminExample = new AdminExample();
    adminExample.createCriteria().andANameEqualTo(name).andAPassEqualTo(MD5Util.getMD5(pwd));
    List<Admin> list = adminMapper.selectByExample(adminExample);
    if(list.size()>0){
       return list.get(0);
    }
    return null;
  }
}
