package com.example.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // AOP 기능 활성화
@Component // bean 등록
public class ControllerAspect {

   long start = 0L;
   long end = 0L;

   @Before(value = "execution (* com.example.basic.controller.*.*(..))")
   public void onBeforeHandler(JoinPoint joinPoint) {
      log.warn("@Before run");
      start = System.currentTimeMillis();
   }

   @After(value = "execution (* com.example.basic.util.*.*(..))")
   public void onAfterHandler(JoinPoint joinPoint) {
      String name = joinPoint.getSignature().getName();
      log.warn(name);
   }

   // @AfterThrowing(value = "execution (*
   // com.example.basic.AuthController.*.*(..))")
   // public void afterThrowing(JoinPoint joinPoint) {
   // log.warn("오류 발생");
   // }

   @AfterReturning(value = "execution (* com.example.basic.util.*.*(..))", returning = "data")
   public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
      if (data != null) {
         log.warn(data.toString());
      }
      log.debug("@AfterReturning run");
   }
}
