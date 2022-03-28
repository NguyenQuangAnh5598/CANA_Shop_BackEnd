package com.example.cana_be.repository;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Long> {
    Optional<Orders> findOrdersByUserAndStatusId(User user, int userId);

    Boolean existsByUserAndStatusId(User user, int id);

    @Query(value = "select * from orders ord join users us on ord.user_id = us.id and us.id = :userId", nativeQuery = true)
    List<Orders> findAllOrderByUserId(@Param("userId") Long userId);

    @Query(value = "select * " +
            "from cana_shop.orders " +
            "where DATE(create_time) >= :startDate and DATE(create_time) <= :endDate " +
            "and status_id = 4 ", nativeQuery = true)
    List<Orders> statisticalOrderByTime(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    @Query(value = "select sum(total_price) as total_revenue " +
            "from cana_shop.orders " +
            "where DATE(create_time) >= :startDate and DATE(create_time) <= :endDate " +
            "and status_id = 4 ", nativeQuery = true)
    double statisticalRevenueByTime(@Param("startDate")Date startDate, @Param("endDate")Date endDate);

    List<Orders> findAllByStatusId(int id);
}
