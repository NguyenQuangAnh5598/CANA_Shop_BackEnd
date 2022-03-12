package com.example.cana_be.service.implement;

import com.example.cana_be.model.Orders;

import com.example.cana_be.model.User;
import com.example.cana_be.repository.IOrderRepo;
import com.example.cana_be.service.extend.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderRepo orderRepo;

    @Override
    public List<Orders> findAll() {
        return orderRepo.findAll();
    }

    @Override
    public Optional<Orders> findById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public void save(Orders orders) {
        orderRepo.save(orders);
    }

    @Override
    public void remove(Long id) {
        orderRepo.deleteById(id);
    }

    @Override
    public void createCurrentOrder(User user) {
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setStatusId(1);
        orderRepo.save(orders);
    }

    @Override
    public Orders findOrdersByUserAndStatusId(User user, int id) {
        Orders orders = orderRepo.findOrdersByUserAndStatusId(user, id).get();
        return orders;
    }

    @Override
    public Boolean existsByUserAndStatusId(User user, int id) {
        return orderRepo.existsByUserAndStatusId(user,id);
    }
}
