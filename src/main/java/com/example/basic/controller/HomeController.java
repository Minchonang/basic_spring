package com.example.basic.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// import com.example.basic.model.Emp;
import com.example.basic.model.Food;
// import com.example.basic.model.Player;
// import com.example.basic.repository.EmpRepository;
import com.example.basic.repository.FoodRepository;
// import com.example.basic.repository.PlayerRepository;
import com.example.basic.repository.OwnerRepository;

@Controller
public class HomeController {

   @Autowired
   HttpSession session;

   @Autowired
   OwnerRepository ownerRepository;

   @Autowired
   FoodRepository foodRepository;

   @GetMapping("/geojeFood")
   @ResponseBody
   public List<Food> pagination(@RequestParam(defaultValue = "1") int p) {
      Pageable page = PageRequest.of(p - 1, 10);
      Page<Food> food = foodRepository.findAll(page);
      return food.getContent();
   }

   @GetMapping("/pagination")
   public String pagination(
         Model model, @RequestParam(defaultValue = "1") int page) {
      int startPage = (page - 1) / 10 * 10 + 1;
      int endPage = startPage + 9;
      model.addAttribute("startPage", startPage);
      model.addAttribute("endPage", endPage);
      model.addAttribute("page", page);
      return "pagination";
   }

}
