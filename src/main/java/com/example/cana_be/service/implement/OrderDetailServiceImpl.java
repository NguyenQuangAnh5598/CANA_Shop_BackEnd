package com.example.cana_be.service.implement;

import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.repository.IOrderDetailRepo;
import com.example.cana_be.service.extend.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    IOrderDetailRepo orderDetailRepo;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepo.findAll();
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepo.findById(id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepo.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        orderDetailRepo.deleteById(id);
    }
}
