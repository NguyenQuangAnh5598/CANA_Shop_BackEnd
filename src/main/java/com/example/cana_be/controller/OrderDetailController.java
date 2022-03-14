package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.OrderDetail;
import com.example.cana_be.model.Orders;
import com.example.cana_be.service.extend.IOrderDetailService;
import com.example.cana_be.service.extend.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orderdetail")
public class OrderDetailController {

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IOrderService orderService;

    @GetMapping
    public ResponseEntity<?> showAllOrderDetail() {
        List<OrderDetail> orderDetailList = orderDetailService.findAll();
        if (orderDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDetailOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewOrderDetail(@RequestBody OrderDetail orderDetail) {
        if (orderDetail.getProduct().getQuantity() == orderDetail.getOrderQuantity()) {
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseMessage("Số lượng hàng không đủ, xin đặt lại sau"), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(orderDetail.getId());
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Không Có"), HttpStatus.NOT_FOUND);
        }
        orderDetailService.save(orderDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetailById(@PathVariable Long id) {
        Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(id);
        if (!orderDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailService.remove(id);
        return new ResponseEntity<>(new ResponseMessage("Delete completed"), HttpStatus.OK);
    }

    @GetMapping("/findAllByOrder/{id}")
    public ResponseEntity<?> findAllByOrder(@PathVariable Long id) {
        Optional<Orders> orders = orderService.findById(id);
        Iterable<OrderDetail> orderDetailList = orderDetailService.findAllByOrders(orders.get());
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }
}
