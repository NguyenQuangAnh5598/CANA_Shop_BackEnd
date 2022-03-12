package com.example.cana_be.repository;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepo extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersByUserAndStatusId(User user, int id);
    Boolean existsByUserAndStatusId(User user,int id);

}
