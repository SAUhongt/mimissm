package com.ht.controller;

import com.ht.pojo.Admin;
import com.ht.service.AdminService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author SRDZ
 * @date 2023/2/9 18:35
 */

@Controller
@RequestMapping("/admin")
public class AdminAction {

  @Autowired
  private AdminService adminService;


  @RequestMapping("/login")
  public String login(String name,String pwd, HttpServletRequest request){
    Admin admin = adminService.login(name,pwd);
    if(admin!=null){
      request.setAttribute("admin",admin);
      return "main";
    }else{
      request.setAttribute("errmsg","用户名或密码不正确");
      return "login";
    }
  }


}
