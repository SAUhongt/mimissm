package com.ht.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ht.pojo.ProductInfo;
import com.ht.pojo.vo.ProductInfoVo;
import com.ht.service.ProductInfoService;
import com.ht.utils.FileNameUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SRDZ
 * @date 2023/2/10 21:53
 */

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

  private static final int PAGE_SIZE=5;

//  String saveFileName = "";
  @Autowired
  ProductInfoService productInfoService;

  @RequestMapping("/getAll")
  public String getAll(Model model){
    List list = productInfoService.getAll();
    model.addAttribute("list",list);
    return "product";
  }

  @RequestMapping("/split")
  public String split(HttpServletRequest request){
    Integer page = (Integer) request.getAttribute("page");
    if(page==null){
      page=new Integer(1);
    }
    PageInfo<ProductInfo> pageInfo = productInfoService.splitPage(page, PAGE_SIZE);
    request.setAttribute("info",pageInfo);
    return "product";
  }

  @RequestMapping("/ajaxsplit")
  @ResponseBody
  public void ajaxSplit(int page,ProductInfoVo vo, HttpSession session){
    PageInfo<ProductInfo> pageInfo = productInfoService.splitPageCondition(page,PAGE_SIZE,vo);
    session.setAttribute("info",pageInfo);
  }

  @RequestMapping("/ajaxImg")
  @ResponseBody
  public Object ajaxIma(MultipartFile pimageFile, HttpServletRequest request){
    String saveFileName = new StringBuilder().append(FileNameUtil.getUUIDFileName())
        .append(FileNameUtil.getFileType(pimageFile.getOriginalFilename())).toString();

    String path = request.getServletContext().getRealPath("/image_big");

    try {
      pimageFile.transferTo(new File(
          new StringBuilder().append(path).append(File.separator).append(saveFileName).toString()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    ObjectMapper objectMapper = new ObjectMapper();
    String imgurl = "";
    Map map = new HashMap<>();
    map.put("imgurl", saveFileName);
    try {
      imgurl = objectMapper.writeValueAsString(map);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return imgurl;
  }

  @RequestMapping("/save")
  public String save(ProductInfo info,HttpServletRequest request){
//    info.setpImage(saveFileName);
    info.setpDate(new Date());
    int result = productInfoService.save(info);
    if(result<1){
      request.setAttribute("msg","添加失败！");
    }else{
      request.setAttribute("msg","添加成功！");
    }
    return "forward:/prod/split.action";
  }

  @RequestMapping("/one")
  public String one(int pid,int page,Model model){
    ProductInfo info = productInfoService.one(pid);
    model.addAttribute("prod",info);
    model.addAttribute("page",page);
    return "update";
  }

  @RequestMapping("/update")
  public String update(ProductInfo info,int page,HttpServletRequest request){
    int result = productInfoService.update(info);
    request.setAttribute("page",page);
    if(result<1){
      request.setAttribute("msg","更新失败！");
    }else{
      request.setAttribute("msg","更新成功！");
    }
    return "forward:/prod/split.action";
  }

  @RequestMapping("/delete")
  public String delete(int pid,HttpServletRequest request){
    int result = productInfoService.deleteById(pid);
    if(result<1){
      request.setAttribute("msg","删除失败！");
    }else{
      request.setAttribute("msg","删除成功！");
    }
    return "forward:/prod/split.action";
  }

  @RequestMapping("deletebatch")
  public String deletebatch(String str,HttpServletRequest request){
    int result = productInfoService.deletebatch(str.split(","));
    if(result>0){
      request.setAttribute("msg","删除成功！");
    }else{
      request.setAttribute("msg","删除失败！");
    }
    return "forward:/prod/split.action";
  }

  @RequestMapping("/condition")
  @ResponseBody
  public void condition(ProductInfoVo vo,HttpSession session){
    List list = productInfoService.selectCondition(vo);
    session.setAttribute("list",list);
  }
}
