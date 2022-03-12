package com.example.cana_be.repository;

import com.example.cana_be.model.Category;
import com.example.cana_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    @Query(value = "select * from product p join category c on p.category_id = c.id and c.category_name like CONCAT('%', :categoryName, '%')", nativeQuery = true)
    public List<Product> findProductByCategoryName(@Param("categoryName") String categoryName);

}
