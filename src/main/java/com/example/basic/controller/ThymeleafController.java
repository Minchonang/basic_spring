package com.example.basic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.basic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.Emp;
import com.example.basic.repository.EmpRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

   @Autowired
   EmpRepository empRepository;

   @Autowired
   ProductRepository productRepository;

   @GetMapping("/empList")
   public String empList(Model model) {
      List<Emp> list = empRepository.findAll();
      model.addAttribute("empList", list);
      return "emp"; // html 파일명
   }

   @GetMapping("userList")
   public String userList(Model model) {
      List<Map<String, Object>> userList = new ArrayList<>();
      Map<String, Object> user = null;
      user = new HashMap<>();
      user.put("userId", "a");
      user.put("userName", "apple");
      user.put("userAge", 21);
      userList.add(user);
      user = new HashMap<>();
      user.put("userId", "b");
      user.put("userName", "banana");
      user.put("userAge", 22);
      userList.add(user);
      user = new HashMap<>();
      user.put("userId", "c");
      user.put("userName", "carrot");
      user.put("userAge", 23);
      userList.add(user);
      model.addAttribute("userList", userList);
      return "userList";
   }

   @GetMapping("user")
   public String user(Model model) {
      Map<String, Object> user = null;
      user = new HashMap<>();
      user.put("userId", "z");
      user.put("userName", "zoo");
      user.put("userAge", 25);
      model.addAttribute("user", user);
      return "user";
   }

   @GetMapping("mode")
   public String mode(Model model, @RequestParam Map<String, Object> map) {
      model.addAttribute("name", map.get("name"));
      model.addAttribute("auth", map.get("auth"));
      model.addAttribute("category", map.get("category"));
      return "mode";
   }

}
