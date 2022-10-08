package com.example.demo.persist.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
// при созданнии новой сущности не забыть прописать ее в Hibernate конфиг. файле
// при созданиипри созданнии  СОСТАВНОЙ сущности СОСТАВНОЙ ПЕРВИЧНЫЙ ключ не подойдет
@Entity            // - не сработает на запросах,  нужен простой отдельный ID
@Table(name = "purchase")
public class PurchaseInfo extends AbstractItem {
    @Id
    @Column
    private Integer id;

    @Column
    private Integer product_id;

    @Column
    private Integer user_id;

    @Column
    private Integer quantity;

    @Column
    private Double totalPrice;


    public PurchaseInfo() {
    }


}
