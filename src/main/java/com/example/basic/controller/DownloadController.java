package com.example.basic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.basic.model.FileAtch;
import com.example.basic.repository.FileAtchRepository;

@Controller
public class DownloadController {
      @Autowired
      FileAtchRepository fileAtchRepository;

      @GetMapping("/download")
      public ResponseEntity<Resource> download(@RequestHeader("Accept-Language") String lang,
                  // 첨부파일 기본키(번호) 받기
                  @ModelAttribute FileAtch filesAtch, @RequestParam int id) throws Exception {
            // FileAtchRepository findById(기본키)
            Optional<FileAtch> opt = fileAtchRepository.findById(id);
            String sName = opt.get().getSaveName();
            String oName = opt.get().getOriginalName();

            // System.out.println(lang);

            File file = new File("/Users/minchonang/Documents/" + sName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                        .header("content-disposition", "filename=" + URLEncoder.encode(oName, "utf-8"))
                        .contentLength(file.length())
                        .contentType(MediaType.parseMediaType("application/octet-stream"))
                        // .contentType(MediaType.parseMediaType("audio/mpeg"))
                        .body(resource);
      }
}
