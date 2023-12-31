package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
