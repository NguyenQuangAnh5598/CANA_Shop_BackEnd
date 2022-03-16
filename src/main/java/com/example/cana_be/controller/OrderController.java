package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import com.example.cana_be.model.Product;
import com.example.cana_be.service.extend.IOrderDetailService;
import com.example.cana_be.service.extend.IOrderService;
import com.example.cana_be.service.extend.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
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
        if (ordersList.isEmpty()) {
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

    @PutMapping("/cancelOrder/{id}")
    public ResponseEntity<?> cancelOrderById(@PathVariable Long id) {
        Orders orders = orderService.findById(id).get();
        //chuyen trang thai order = 5
        orders.setStatusId(5);
        List<OrderDetail> orderDetailList = (List<OrderDetail>) orderDetailService.findAllByOrders(orders);
        //lay danh sach orderdetail, duyet danh sach
        for (int i = 0; i < orderDetailList.size(); i++) {
            //lay so luong product trong bang orderdetail
            int quantityOfOrderDetail = orderDetailList.get(i).getOrderQuantity();
            //lay so luong product trong bang product
            int productQuantity = orderDetailList.get(i).getProduct().getQuantity();
            //set lai so luong bang product
            productQuantity += quantityOfOrderDetail;
            orderDetailList.get(i).getProduct().setQuantity(productQuantity);
            //update product
            productService.save(orderDetailList.get(i).getProduct());
        }
        //update order
        orderService.save(orders);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/acceptOrder/{id}")
    public ResponseEntity<?> acceptOrder(@PathVariable Long id) {
        Orders orders = orderService.findById(id).get();
        if (orders.getStatusId() == 2) {
            orders.setStatusId(3);
        } else if (orders.getStatusId() == 3) {
            orders.setStatusId(4);
        }
        orderService.save(orders);
        return new ResponseEntity<>("OK", HttpStatus.OK);
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
            if (!orderDetailList.isEmpty()) {
                orders.get().setStatusId(2);
                orders.get().setCreateTime(Timestamp.from(Instant.now()));
                orderService.save(orders.get());
                if (!orderService.existsByUserAndStatusId(orders.get().getUser(), 1)) {
                    orderService.createCurrentOrder(orders.get().getUser());
                    return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>(new ResponseMessage("NO"), HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>(errorOrderDetail, HttpStatus.OK);
        }

    }
}
