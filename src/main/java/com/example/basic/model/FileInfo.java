package com.example.basic.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileInfo {
   private MultipartFile file;
   // private MultipartFile[] file; -> 여러 개 업로드 가능
}
