package com.ht.pojo.vo;

/**
 * @author SRDZ
 * @date 2023/2/18 16:32
 */
public class ProductInfoVo {

  private String pname;
  private String typeid;
  private String lprice;
  private String hprice;

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public String getTypeid() {
    return typeid;
  }

  public void setTypeid(String typeid) {
    this.typeid = typeid;
  }

  public String getLprice() {
    return lprice;
  }

  public void setLprice(String lprice) {
    this.lprice = lprice;
  }

  public String getHprice() {
    return hprice;
  }

  public void setHprice(String hprice) {
    this.hprice = hprice;
  }

  @Override
  public String toString() {
    return "productInfoVo{" +
        "pname='" + pname + '\'' +
        ", typeid='" + typeid + '\'' +
        ", lprice='" + lprice + '\'' +
        ", hprice='" + hprice + '\'' +
        '}';
  }
}
