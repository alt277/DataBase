package com.example.demo.persist.entity.data;


import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.User;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
public class ProductDTO {

    private Integer id;

    private String name;

    private Double price;



    public ProductDTO() {
    }

    public ProductDTO(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductDTO(Integer id, String name, Double price, List<User> users) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.users = users;
    }
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price =product.getPrice();
    }

    private List<User> users;

}