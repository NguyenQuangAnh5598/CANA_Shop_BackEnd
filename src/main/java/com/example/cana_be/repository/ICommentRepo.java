package com.example.cana_be.repository;

import com.example.cana_be.model.Commentt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepo extends JpaRepository<Commentt, Long> {
     List<Commentt> findAllByProductIdOrderById(Long productId);
}
