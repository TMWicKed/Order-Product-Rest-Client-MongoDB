package com.harman.RestClient_MongoDB.repository;

import com.harman.RestClient_MongoDB.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Orders, Integer> {
    List<Orders> findOrderByUserIdAndQuantityGreaterThan(Integer id, Integer quantity);
}
