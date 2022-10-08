package com.example.demo.persist.entity.data;


import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.User;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
public class ProductDTO {

    @NotNull(message = "Id не может быть null")
    private Integer id;

    @NotNull(message = "Имя не может быть null")
    @Size(min = 1, max = 50, message
            = "Имя должно быть от 1 символа")
    private String name;

    @NotNull(message = "Цена не может быть null")
    private Double price;

    private List<User> users;

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



}