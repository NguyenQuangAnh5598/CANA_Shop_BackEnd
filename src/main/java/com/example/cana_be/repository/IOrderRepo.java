package com.example.cana_be.repository;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersByUserAndStatusId(User user, int id);
    Boolean existsByUserAndStatusId(User user,int id);

}
