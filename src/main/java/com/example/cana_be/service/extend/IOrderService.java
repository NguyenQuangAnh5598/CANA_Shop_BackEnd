package com.example.cana_be.service.extend;

import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import com.example.cana_be.service.IGeneralService;

import java.util.List;

public interface IOrderService extends IGeneralService<Orders> {
    void createCurrentOrder(User user);

    Orders findOrdersByUserAndStatusId(User user, int id);

    Boolean existsByUserAndStatusId(User user,int id);

    List<Orders> findAllOrderByUserId(Long userId);
}
