package com.example.cana_be.repository;

import com.example.cana_be.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "select * from category where category_name like CONCAT('%', :categoryName, '%')", nativeQuery = true)
    public List<Category> findCategoryByName(@Param("categoryName") String categoryName);
}
