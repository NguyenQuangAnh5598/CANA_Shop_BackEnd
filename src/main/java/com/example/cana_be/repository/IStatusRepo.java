package com.example.cana_be.repository;

import com.example.cana_be.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepo extends JpaRepository<Status,Long> {
}
