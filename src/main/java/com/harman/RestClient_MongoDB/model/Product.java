package com.harman.RestClient_MongoDB.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;
    private String name;
    private String description;
    private Double price;
}
