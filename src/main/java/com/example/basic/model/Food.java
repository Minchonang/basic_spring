package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data // toString() Override
public class Food {
   @Id
   int id;
   String name;
   String address;
   String desc;
   String tel;
   String latitude;
   String longitude;
}
