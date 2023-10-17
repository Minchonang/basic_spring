package com.example.basic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpCheckFilter implements Filter {
   @Override
   public void doFilter(
         ServletRequest request, ServletResponse response,
         FilterChain chain) throws IOException, ServletException {
      log.warn("filter begin");
      HttpServletRequest req = (HttpServletRequest) request;
      String ip = request.getRemoteAddr();
      log.warn("ip : " + ip);
      chain.doFilter(req, response);
      log.warn("filter end");
   }
}
