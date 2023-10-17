package com.example.basic.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.basic.filter.IpCheckFilter;

@Configuration
public class FilterConfig {
   @Bean
   public FilterRegistrationBean<Filter> getFilterRegistrationBean() {
      FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new IpCheckFilter());
      bean.addUrlPatterns("/*");
      return bean;
   }
}
