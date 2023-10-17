package com.example.basic.repository;

import com.example.basic.model.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
   // public abstract Owner findByIdAndName(int id, String name);
   Owner findByIdAndName(int id, String name);

}
