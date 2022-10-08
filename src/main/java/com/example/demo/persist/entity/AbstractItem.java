package com.example.demo.persist.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Data
@MappedSuperclass
public abstract class AbstractItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
 //   @JsonView(CommonView.Id.class)
    private Integer id;

    @CreationTimestamp
 //   @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
//    @JsonView(CommonView.CommonFull.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd ")
    private LocalDate createDate;






}
