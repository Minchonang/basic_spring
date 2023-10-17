package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.DShop;

@Repository
public interface DShopRepository extends JpaRepository<DShop, Integer> {

}
