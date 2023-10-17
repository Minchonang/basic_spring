package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "animal")
public class Product {
   @Id
   int id;
   String name;
   int price;

   @JsonIgnore
   @ManyToOne
   Animal animal;
}
