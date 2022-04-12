package com.example.cana_be.repository;

import com.example.cana_be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Comment, Long> {
     List<Comment> findAllByProductId(Long productId);
}
