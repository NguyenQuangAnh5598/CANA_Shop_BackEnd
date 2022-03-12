package com.example.cana_be.repository;

import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findAllByOrders(Orders orders);
}
