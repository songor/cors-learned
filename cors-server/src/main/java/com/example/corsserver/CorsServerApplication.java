package com.example.corsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CorsServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CorsServerApplication.class, args);
  }

  //  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setFilter(new CorsFilter());
    return filterRegistrationBean;
  }
}
