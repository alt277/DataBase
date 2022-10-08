package com.example.demo.persist.repository;

import com.example.demo.persist.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "select  p.product_id  FROM purchase p  \n" +
            " join product pro on pro.id = p.product_id    \n" +
            " where p.create_date > :date \n" +
            " group by p.product_id \n" +
            " order by sum (p.quantity) desc limit 1 ", nativeQuery = true)
    Integer findBestSellerInt(@Param("date") LocalDate date);


    @Query(" select p from Product  p where p.id =2"
    )
    Product findBestSellerPlain();
}
