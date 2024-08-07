package com.harman.RestClient_MongoDB.controller;

import com.harman.RestClient_MongoDB.model.Orders;
import com.harman.RestClient_MongoDB.service.OrderService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/all")
    public List<Orders> getAll(){
        return service.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Orders> getById(@PathVariable Integer id){
        Orders order=service.findById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        return ResponseEntity.ok(service.save(order));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Integer id, @RequestBody Orders order){
        return ResponseEntity.ok(service.update(id, order));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quantity/totalPrice")
    public List<Orders> getOrderByQuantityAndTotalPrice(){
        return service.getOrderByQuantityAndTotalPrice();
    }

    @GetMapping("/groupByQuantity")
    public Map<Integer, List<Orders>> getOrdersGroupedByQuantity() {
        return service.groupByQuantity();
    }

    @GetMapping("/oderIdQuantity/{id}/{quantity}")
    public ResponseEntity<List<Orders>> findOrderByUserIdAndQuantityGreaterThan(@PathVariable Integer id, @PathVariable Integer quantity) {
        return ResponseEntity.ok(service.findOrderByUserIdAndQuantityGreaterThan(id, quantity));
    }
}
