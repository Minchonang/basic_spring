package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Board {
   @Id
   @GeneratedValue

   int id;
   String title;
   String writer;
   String content;

   @OneToMany(mappedBy = "board")
   List<Comment> comments = new ArrayList<>();

   @OneToMany(mappedBy = "board")
   List<FileAtch> fileAtchs = new ArrayList<>();

}
