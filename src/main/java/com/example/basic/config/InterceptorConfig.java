package com.example.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.basic.interceptor.SignInCheckInterceptor;

// @Configuration
public class InterceptorConfig implements WebMvcConfigurer {

   @Autowired
   private SignInCheckInterceptor signInCheckInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(signInCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/auth/signin",
            "/auth/signup");
      WebMvcConfigurer.super.addInterceptors(registry);
   }
}