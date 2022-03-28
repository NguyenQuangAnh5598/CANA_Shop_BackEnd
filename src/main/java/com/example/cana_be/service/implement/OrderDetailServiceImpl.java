package com.example.cana_be.service.implement;

import com.example.cana_be.dto.request.OrderDetailForm;
import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import com.example.cana_be.model.Product;
import com.example.cana_be.repository.IOrderDetailRepo;
import com.example.cana_be.repository.IProductRepo;
import com.example.cana_be.security.userprincal.UsersDetailService;
import com.example.cana_be.service.extend.IOrderDetailService;
import com.example.cana_be.service.extend.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailServiceImpl implements IOrderDetailService {

    @Autowired
    IProductRepo productRepo;

    @Autowired
    UsersDetailService usersDetailService;

    @Autowired
    IOrderDetailRepo orderDetailRepo;

    @Autowired
    IOrderService orderService;

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
    public void createNewOrderDetail(OrderDetailForm orderDetailForm) {
        OrderDetail orderDetail = new OrderDetail();
        Orders orders = orderService.findOrdersByUserAndStatusId(usersDetailService.getCurrentUser(), 1);
        orderDetail.setOrders(orders);
        Optional<Product> product = productRepo.findById(orderDetailForm.getProductId());
        boolean check = true;
        List<OrderDetail> orderDetailList = (List<OrderDetail>) findAllByOrders(orders);
        for (int i = 0; i < orderDetailList.size(); i++) {
            if (product.get().getId() == orderDetailList.get(i).getProduct().getId()) {
              orderDetail.setOrderQuantity(orderDetailList.get(i).getOrderQuantity() + orderDetailForm.getOrderQuantity());
              orderDetail.setId(orderDetailList.get(i).getId());
              check = false;
            }
        }
        if (check) {
            orderDetail.setOrderQuantity(orderDetailForm.getOrderQuantity());
        }
        orderDetail.setProduct(product.get());
        orderDetailRepo.save(orderDetail);
    }

    @Override
    public void remove(Long id) {
        orderDetailRepo.deleteById(id);
    }

    @Override
    public Iterable<OrderDetail> findAllByOrders(Orders orders) {
        return orderDetailRepo.findAllByOrders(orders);
    }
}
