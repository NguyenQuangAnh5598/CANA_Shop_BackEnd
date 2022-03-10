package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Orders;
import com.example.cana_be.service.extend.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping
    public ResponseEntity<?> showAllOrder() {
        List<Orders> ordersList = orderService.findAll();
        if (ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ordersList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = orderService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersOptional.get(),HttpStatus.OK);
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
            return new ResponseEntity<>(new ResponseMessage("Không Có"),HttpStatus.NOT_FOUND);
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
        return new ResponseEntity<>(new ResponseMessage("Delete completed"),HttpStatus.OK);
    }

}
