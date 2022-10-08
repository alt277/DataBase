package com.example.demo.persist.repository;

import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.data.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDataRepository extends JpaRepository <Product,Integer>{

    @Query("select new com.example.demo.persist.entity.data.ProductDTO (p.id, p.name, p.price) from Product p " +
            " where p.id = :id  ")
   Optional <ProductDTO> findProductById (@Param("id") Integer id);


    @Query("select new com.example.demo.persist.entity.data.ProductDTO (p.id, p.name, p.price) from Product p")
    List<ProductDTO> findAllProductData();

//    @Query("select new com.example.demo.persist.entity.data.ProductDTO (prod.id,prod.name,prod.price,prod.users) from Product prod  " +
//            " JOIN  PurchaseInfo p on  p.product_id =prod.id" +
//            " JOIN User u on p.user_id =u.id "+
//            " where p.id = :id  ")
//    ProductDTO findWholeProductById (@Param("id") Integer id);

    @Query("select new com.example.demo.persist.entity.data.ProductDTO (d.id,d.name,d.price) from Product  " +
            " d join d.users u "+
            " where u.id = :id  ")

   List <ProductDTO> findProductByUserId (@Param("id") Integer id);


//    @Query(value =" select new com.example.demo.persist.entity.data.ProductDTO (pr.id,pr.name,pr.price) from Product pr  \n" +
//            " where pr.id = " +
//            "( select  p.product_id  FROM purchase p  \n" +
//            " join product pro on pro.id = p.product_id    \n" +
//            " where p.create_date > :date \n" +
//            " group by p.product_id \n" +
//            " order by sum (p.quantity) desc limit 1 )", nativeQuery = true )
//
//    ProductDTO  findBestSellerByDate(@Param("date") LocalDate date);


}
