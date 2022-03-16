package com.example.cana_be.repository;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersByUserAndStatusId(User user, int userId);
    Boolean existsByUserAndStatusId(User user,int id);

    @Query(value = "select * from orders ord join users us on ord.user_id = us.id and us.id = :userId", nativeQuery = true)
    List<Orders> findAllOrderByUserId(@Param("userId") Long userId);




}
