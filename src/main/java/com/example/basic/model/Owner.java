package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Owner {
   @Id
   int id;
   String name;
   String pwd;
   // int에 null을 포함하는 방법:
   // @Column(true)

   @OneToMany(mappedBy = "owner")
   List<Animal> animals = new ArrayList<>();

   public int size() {
      return 0;
   }
}
