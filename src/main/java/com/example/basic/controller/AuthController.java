package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.basic.model.Owner;
import com.example.basic.repository.OwnerRepository;
import com.example.basic.util.Encrypt;

// import java.math.BigInteger;
// import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
   @Autowired
   HttpSession session;

   @Autowired
   PasswordEncoder passwordEncoder;

   @Autowired
   OwnerRepository ownerRepository;

   @Autowired
   Encrypt encrypt;

   @GetMapping("login") // 8080/auth/login
   public String login() {
      session.setAttribute("user_id", "minchonang");

      return "redirect:/empList"; // redirect: 재요청
      // return "auth/login";
   }

   @GetMapping("logout")
   public String logout() {
      session.removeAttribute("user_id");

      return "logout";
      // return "redirect:/empList";
      // return "auth/login";
   }

   @GetMapping("/ownerLogin")
   @ResponseBody
   // public Owner ownerLogin(
   // @RequestParam int id, @RequestParam String name) {
   // Owner owner = ownerRepository.findByIdAndName(id, name);
   public Owner ownerLogin(
         @RequestParam int id,
         @RequestParam String name,
         @RequestParam Map<String, String> map) {

      Owner owner = ownerRepository.findByIdAndName(
            Integer.parseInt(
                  map.get("id")),
            map.get("name"));

      // System.out.println(owner);

      if (owner != null) {
         session.setAttribute("id", id);
         session.setAttribute("name", name);

      }

      return owner;

   }

   @GetMapping("id-check")
   @ResponseBody
   public String idCheck(@ModelAttribute Owner owner) {
      int id = owner.getId();
      Optional<Owner> opt = ownerRepository.findById(id);
      if (opt.isPresent()) { // 값 있음, id 있음, 가입 불가
         return "가입 불가";
      } else { // 값 없음, id 없음, 가입 가능
         return "가입 가능";
      }

   }

   // 로그인
   @GetMapping("signin")
   public String signinPost(@ModelAttribute Owner owner) throws NoSuchAlgorithmException {
      String pwd = passwordEncoder.encode("1");
      System.out.println(pwd);

      String pwd2 = encrypt.encode("1");
      System.out.println(pwd2);

      return "auth/signin";
   }

   @PostMapping("signin")
   public String signinGet(@ModelAttribute Owner owner) {
      int id = owner.getId();

      Optional<Owner> result = ownerRepository.findById(id);

      boolean isPresent = result.isPresent();

      if (isPresent) {
         String pwd = result.get().getPwd();
         String name = result.get().getName();
         boolean isMatch = passwordEncoder.matches(owner.getPwd(), pwd);

         if (isMatch) {
            session.setAttribute("id", id);
            session.setAttribute("name", name);
         }
      }

      return "redirect:/board/";
   }

   // 회원가입
   @GetMapping("signup")
   public String signupGet(@ModelAttribute Owner owner) {
      return "auth/signup";
   }

   @PostMapping("signup")
   public String signupPost(@ModelAttribute Owner owner) {

      String newPwd = passwordEncoder.encode(owner.getPwd());
      owner.setPwd(newPwd);
      ownerRepository.save(owner);

      return "redirect:/auth/signin";
   }

   @GetMapping("emp")
   public String emp() {
      return "emp";
   }

}
