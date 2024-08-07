package com.harman.RestClient_MongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private double totalPrice;
    private Integer userId;

    @Field(name = "created_at")
    private Date orderDate;
}
