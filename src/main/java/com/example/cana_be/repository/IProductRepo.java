package com.example.cana_be.repository;

import com.example.cana_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepo extends JpaRepository<Product,Long> {
    @Modifying
    @Query(value = "select * from product p where p.name like %:name%",nativeQuery=true)
    List<Product> findByName(String name);
}
