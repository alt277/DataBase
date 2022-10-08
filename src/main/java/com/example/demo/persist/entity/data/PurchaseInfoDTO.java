package com.example.demo.persist.entity.data;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseInfoDTO{

    private Integer id;

    private Integer product_id;

    private Integer user_id;

    private Integer quantity;

    private Double totalPrice;




}
