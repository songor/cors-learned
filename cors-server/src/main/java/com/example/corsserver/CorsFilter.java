package com.example.corsserver;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;

public class CorsFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    // response.addHeader("Access-Control-Allow-Origin", "http://localhost:8090");
    // Cookie 跨域 Access-Control-Allow-Origin 不能为 *
    // response.addHeader("Access-Control-Allow-Origin", "*");
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String origin = request.getHeader("Origin");
    if (!StringUtils.isEmpty(origin)) {
      response.addHeader("Access-Control-Allow-Origin", origin);
    }

    // response.addHeader("Access-Control-Allow-Methods", "GET,POST");
    response.addHeader("Access-Control-Allow-Methods", "*");

    // response.addHeader("Access-Control-Allow-Headers", "content-type");
    response.addHeader("Access-Control-Allow-Headers", "*");

    response.addHeader("Access-Control-Max-Age", "3600");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}
