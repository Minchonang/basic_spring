package com.example.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
