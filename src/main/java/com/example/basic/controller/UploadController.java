package com.example.basic.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.basic.model.Board;
import com.example.basic.model.FileAtch;
import com.example.basic.model.FileInfo;
import com.example.basic.repository.FileAtchRepository;

@Controller
public class UploadController {

   @Autowired
   FileAtchRepository fileAtchRepository;

   @GetMapping("/upload1")
   public String upload1() {
      return "upload1";
   }

   @PostMapping("/upload1")
   @ResponseBody
   public String upload1Post(MultipartHttpServletRequest mRequest) {

      MultipartFile mFile = mRequest.getFile("file");
      String filename = mFile.getOriginalFilename();
      long size = mFile.getSize();

      File folder = new File("/Users/minchonang/Documents/");
      folder.mkdirs();

      File file = new File("/Users/minchonang/Documents/" + filename);

      try {
         mFile.transferTo(file);
      } catch (IllegalStateException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      return "파일 이름: " + filename + ": " + size + "KB";

   }

   @PostMapping("/upload2")
   @ResponseBody
   public String upload2(@RequestParam("file") MultipartFile mFile) {

      // oName: original name
      // sName: save name
      String result = "";
      String oName = mFile.getOriginalFilename();
      result += oName + "<br>" + mFile.getSize();

      // 1. 중복 파일이 존재하는지 확인하기.
      File file = new File("/Users/minchonang/Documents/" + oName);
      boolean isFile = file.isFile();

      // 2. 중복 파일이 있다면 파일명 변경하기.
      String sName = "";
      if (isFile) {
         // ex. abc.jpg -> abc_12312445.jpg
         String name = oName.substring(0, oName.indexOf('.'));
         String ext = oName.substring(oName.indexOf('.'));
         sName = name + "_" + System.currentTimeMillis() + ext;

         System.out.println("저장된 파일 이름:" + sName);
      }

      try {
         mFile.transferTo(new File("/Users/minchonang/Documents/" + sName));
      } catch (IllegalStateException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

      // 첨부파일의 정보를 DB에 저장.
      FileAtch fileAtch = new FileAtch();
      fileAtch.setOriginalName(oName); // 원본 파일명
      fileAtch.setSaveName(sName); // 바뀐 파일명
      Board board = new Board();
      board.setId(1); // 무결성 제약 조건
      fileAtch.setBoard(board);

      fileAtchRepository.save(fileAtch);

      return result;
   }

   @PostMapping("/upload3")
   @ResponseBody
   public String upload3Post(@ModelAttribute FileInfo info) {
      String result = "";
      String oName = info.getFile().getOriginalFilename();
      result += oName + "<br>" + info.getFile().getSize();
      return result;
   }

   @PostMapping("/upload4")
   @ResponseBody
   public String upload4Post(
         @RequestParam("file") MultipartFile[] mFiles) {
      String result = "";
      // for(int i = 0; i < mFiles.length; i++) {
      // MultipartFile mFile = mFiles[i]; }

      for (MultipartFile mFile : mFiles) {
         String oName = mFile.getOriginalFilename();
         result += "파일명: " + oName + "<br>용량: " + mFile.getSize() + "Byte<br>";
      }
      return result;
   }

   @GetMapping("/upload6")
   public String upload6() {
      return "/upload6";
   }

   @PostMapping("/upload6")
   @ResponseBody
   public String upload6Post(MultipartHttpServletRequest mRequest) {
      String result = "";
      Iterator<String> fileNames = mRequest.getFileNames();
      while (fileNames.hasNext()) {
         String fileName = fileNames.next();
         List<MultipartFile> mFiles = mRequest.getFiles(fileName);
         for (MultipartFile mFile : mFiles) {
            String oName = mFile.getOriginalFilename();
            long size = mFile.getSize();
            result += "파일명: " + oName + "<br> " + size + "Byte<br>";
         }
      }
      return result;
   }
}