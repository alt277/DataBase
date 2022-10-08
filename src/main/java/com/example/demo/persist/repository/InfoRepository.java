package com.example.demo.persist.repository;


import com.example.demo.persist.entity.PurchaseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface InfoRepository extends JpaRepository<PurchaseInfo, Integer> {


    @Query("select p from PurchaseInfo p where  p.createDate > :create")
    List<PurchaseInfo> findAllByCreateDate(@Param("create") LocalDate create);

    @Query("select p from PurchaseInfo p")
    List<PurchaseInfo> findAllBy();


    @Query(value = " select  p.product_id  FROM purchase p  \n" +
            " join product pro on pro.id = p.product_id    \n" +
            " where p.create_date > :date \n" +
            " group by p.product_id \n" +
            " order by sum (p.quantity) desc limit 1 ", nativeQuery = true)
    Integer findPID(@Param("date") LocalDate date);


    @Query(value = "select   p.user_id  FROM purchase p \n" +
            " join product pro on pro.id= p.product_id \n" +
            " join users u on u.id = p.user_id \n" +
            "  where  p.create_date > :date " +
            " group by p.user_id \n" +
            " order by sum(p.quantity) desc limit 1"
            , nativeQuery = true)
    Integer findBestUserIdByDate(@Param("date") LocalDate date);


    @Query(value = " select  p.product_id as s FROM purchase p \n" +
            "    join product pro on pro.id= p.product_id \n" +
            "\tjoin users u on u.id = p.user_id\n" +
            "where  u.age =:age\n" +
            "group by p.user_id, p.product_id \n" +
            "order by  p.user_id,sum(p.quantity) desc limit 1 "
            , nativeQuery = true)
    Integer findProductIdByUserAge(@Param("age") Integer age);

}

