package com.harman.RestClient_MongoDB.service;


import com.harman.RestClient_MongoDB.exception.ResourceNotFoundException;
import com.harman.RestClient_MongoDB.model.Orders;
import com.harman.RestClient_MongoDB.model.Product;
import com.harman.RestClient_MongoDB.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    RestClient client;

    @Autowired
    OrderRepository repository;

    @Value("http://localhost:8080/products")
    private String productUrl;

    public List<Orders> findAll() {
        return repository.findAll();
    }

    public Orders findById(Integer id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found!!"));
    }

    public Orders save(Orders order) {
        Product product=getProductById(order.getProductId());
        double totalPrice=product.getPrice()*order.getQuantity();
        order.setTotalPrice(totalPrice);
        if(order.getOrderDate()==null)
            order.setOrderDate(new Date());
        return repository.save(order);
    }

    public Orders update(Integer id, Orders order) {
        Orders order1=findById(id);
//        Product product=getProductById(order.getProductId());
        if (order.getQuantity()!=null)
            order1.setQuantity(order.getQuantity());
//        double totalPrice= product.getPrice()* order1.getQuantity();
//        order1.setTotalPrice(totalPrice);
        if(order.getOrderDate()!=null)
            order1.setOrderDate(order.getOrderDate());

//        return repository.save(order1);
        return save(order1);
    }

    private Product getProductById(Integer productId) {
        try {
            return client.get()
                    .uri(productUrl+"/get/"+productId)
                    .retrieve()
                    .body(Product.class);
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("PRODUCT NOT FOUND!!");
        }
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Orders> getOrderByQuantityAndTotalPrice() {
        List<Orders> orders = repository.findAll();
        List<Orders> order = new ArrayList<>();
        for (Orders order1 : orders)
            if (order1.getQuantity()>=0 && order1.getTotalPrice()>=0){
                order.add(order1);
            }
        return order;
    }

    public Map<Integer, List<Orders>> groupByQuantity() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Orders::getQuantity));
    }


    public List<Orders> findOrderByUserIdAndQuantityGreaterThan(Integer id, Integer quantity) {
        List<Orders> orders = repository.findOrderByUserIdAndQuantityGreaterThan(id, quantity);
        int numberOfOrders=0;
        for (Orders orders1 : orders){
            numberOfOrders++;
        }
        System.out.println(numberOfOrders);
        return orders;
    }
}
