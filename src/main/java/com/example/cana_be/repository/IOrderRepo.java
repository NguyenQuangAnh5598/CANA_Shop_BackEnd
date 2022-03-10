package com.example.cana_be.repository;

import com.example.cana_be.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Orders,Long> {
}
