package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "shop")
@Data
public class DShop {
   @Id
   int shopId;
   String shopName;
   String shopDesc;
   String restDate;
   String parkingInfo;
}
