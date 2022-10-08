package com.example.demo.persist.repository;

import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product,Integer>{

//    @Query(value =" select pr from Product  pr where pr.id = " +
//                "( select  p.product_id  FROM purchase p  \n" +
//                " join product pro on pro.id = p.product_id    \n" +
//                " where p.create_date > :date \n" +
//                " group by p.product_id \n" +
//                " order by sum (p.quantity) desc limit 1 )", nativeQuery = true )
//
//     Product  findBestSellerByDate(@Param("date") LocalDate date);

    @Query(value ="select  p.product_id  FROM purchase p  \n" +
            " join product pro on pro.id = p.product_id    \n" +
            " where p.create_date > :date \n" +
            " group by p.product_id \n" +
            " order by sum (p.quantity) desc limit 1 ", nativeQuery = true )

    Integer  findBestSellerInt(@Param("date") LocalDate date);

//    @Query(value = "  select pro  from purchase pro\n" +
//            " where pro.user_id =(\n" +
//            "select   pro.user_id  FROM purchase p \n" +
//            "join product pro on pro.id= p.product_id \n" +
//            "join users u on u.id = p.user_id\n" +
//            " where  p.create_date >'2022-01-01 12:39:52.963'\n" +
//            "group by p.user_id \n" +
//            "order by sum(p.quantity) desc limit 1 ) \n" +
//            "limit 1", nativeQuery = true)
//    Product findBestSellerByDate(@Param("date") LocalDate date);


    @Query(" select p from Product  p where p.id =2"
           )
     Product  findBestSellerPlain();
}
