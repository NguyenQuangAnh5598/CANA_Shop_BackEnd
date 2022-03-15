package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import com.example.cana_be.service.extend.IOrderDetailService;
import com.example.cana_be.service.extend.IOrderService;
import com.example.cana_be.service.extend.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductService productService;

    @GetMapping
    public ResponseEntity<?> showAllOrder() {
        List<Orders> ordersList = orderService.findAll();
        if (ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = orderService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/findAllOrderByUserId")
    public ResponseEntity<?> findAllOrderByUserId(@RequestParam Long userId) {
        List<Orders> ordersList = orderService.findAllOrderByUserId(userId);
        if(ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewOrder(@RequestBody Orders orders) {
        orderService.save(orders);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Orders orders) {
        Optional<Orders> ordersOptional = orderService.findById(orders.getId());
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Không Có"), HttpStatus.NOT_FOUND);
        }
        orderService.save(orders);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = orderService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.remove(id);
        return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<?> payment(@PathVariable Long id) {
        Optional<Orders> orders = orderService.findById(id);
        List<OrderDetail> orderDetailList = (List<OrderDetail>) orderDetailService.findAllByOrders(orders.get());
        List<OrderDetail> errorOrderDetail = new ArrayList<>();
        for (int i = 0; i < orderDetailList.size(); i++) {
            if (orderDetailList.get(i).getOrderQuantity() > orderDetailList.get(i).getProduct().getQuantity()) {
                errorOrderDetail.add(orderDetailList.get(i));
                orderDetailService.remove(orderDetailList.get(i).getId());
            }
        }
        if (errorOrderDetail.isEmpty()) {
            for (int i = 0; i < orderDetailList.size(); i++) {
                productService.setQuantity(orderDetailList.get(i).getProduct(), orderDetailList.get(i).getOrderQuantity());
            }
            orders.get().setStatusId(2);
            orderService.save(orders.get());
            orderService.createCurrentOrder(orders.get().getUser());
            return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
        }
        return new ResponseEntity<>(errorOrderDetail, HttpStatus.OK);
    }
}
