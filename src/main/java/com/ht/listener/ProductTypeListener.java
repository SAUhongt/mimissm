package com.ht.listener;

import com.ht.service.ProductTypeService;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author SRDZ
 * @date 2023/2/11 17:40
 */
@WebListener
public class ProductTypeListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-*.xml");
    ProductTypeService productTypeService = context.getBean("ProductTypeService",
        ProductTypeService.class);
    List typeList = productTypeService.getAll();
    servletContextEvent.getServletContext().setAttribute("typeList",typeList);
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }
}
