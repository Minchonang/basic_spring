package com.example.basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
   public List<Emp> findByJob(String job);

   public List<Emp> findBySalGreaterThanEqual(int sal);

}
