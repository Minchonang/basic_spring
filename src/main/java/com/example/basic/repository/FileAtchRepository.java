package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.FileAtch;

@Repository
public interface FileAtchRepository extends JpaRepository<FileAtch, Integer> {

}
