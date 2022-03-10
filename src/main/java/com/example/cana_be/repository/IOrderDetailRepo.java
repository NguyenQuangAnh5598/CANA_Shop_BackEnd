package com.example.cana_be.repository;

import com.example.cana_be.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Long> {
}
