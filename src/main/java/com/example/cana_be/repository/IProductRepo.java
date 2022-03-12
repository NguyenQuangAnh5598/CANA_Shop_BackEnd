package com.example.cana_be.repository;

import com.example.cana_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepo extends JpaRepository<Product,Long> {

}
