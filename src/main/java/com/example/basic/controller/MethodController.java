package com.example.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MethodController {

   @Autowired
   JdbcTemplate jt;

   @GetMapping("req/get")
   @ResponseBody
   public String get() {
      return "GET";
   }

   @PostMapping("req/post")
   @ResponseBody
   public String post() {
      return "POST";
   }

   @GetMapping("req/data")
   @ResponseBody
   public List<Map<String, Object>> reqData(
         @RequestParam(required = false) String area,
         @RequestParam(required = false) String score) {

      List<Map<String, Object>> list = jt
            .queryForList("select * from holiday_parking" + " where address like concat('%', ?, '%')", "광주");

      Map<String, String> map = new HashMap<>();
      map.put("area", area);
      map.put("score", score);
      return list;
   }

}
