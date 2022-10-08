package com.example.demo.rest.controller;



import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.data.ProductDTO;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



@RequestMapping("/api/v1/product")
@RestController
public class ProductController {



    private final ProductService productService;


    @Autowired
    public ProductController( ProductService productService) {



        this.productService = productService;
    }
    //вывести самый покупаемый товар за последний месяц
    @GetMapping(path = "/bestSeller", produces = "application/xml")
    public ProductDTO findBestSellerForMonth() {
        LocalDate date = LocalDate.now();
        System.out.println("сегодня:"+date);
        date=date.minusMonths(1);
        return productService.findBestSellerForMonth(date);
    }
    // что чаше всего поупают люди в определенном возрасте
    @GetMapping(path = "/age/{age}", produces = "application/xml")
    public ProductDTO findProductByUserAge(@PathVariable("age") Integer age) {
        return productService.findPrroductByUserAge(age);
    }

    @GetMapping(path = "/bestSellerPlain", produces = "application/xml")
    public Product findBestSellerPlain() {

        return productService.findBestSellerPlain();
    }

//    @GetMapping(path = "/bestSellerNot", produces = "application/xml")
//    public Product findBestSeller() {
//
//        LocalDate date = LocalDate.now();
//        date=date.minusMonths(1);
//        System.out.println("Дата минус месяц =" +date);
//
//        return productService.findBestSellerNot(date);
//    }

    @GetMapping(path = "/all", produces = "application/xml")
    public List<Product> findAll() {
        return productService.findAll();
    }


    @GetMapping(path = "/allDTO", produces = "application/xml")
    public List<ProductDTO> findAllDTO() {
        return productService.findAllDto();
    }

    @GetMapping(path = "/{id}/id", produces = "application/xml")
    public Optional findById(@PathVariable("id") int id) {
        return productService.findById(id);
    }

    @GetMapping(path = "/byUser/{id}", produces = "application/xml")
    public List<ProductDTO> findByIdDto(@PathVariable("id") int id) {
        return productService.findProductByUserId(id);
    }


    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public Product createProduct(@RequestBody Product product) {

        return productService.save(product);

    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@RequestBody Product product) {
// todo

        return productService.update(product);

    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/xml")
    public void deleteById(@PathVariable("id") Integer id) {

        System.out.println("in deleteByID");
        productService.deleteById(id);
    }




    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}