package com.example.cana_be.repository;

import com.example.cana_be.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepo extends JpaRepository<Category, Long> {
}
