package com.example.demo.service;


import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.data.ProductDTO;
import com.example.demo.persist.repository.InfoRepository;
import com.example.demo.persist.repository.ProductDataRepository;
import com.example.demo.persist.repository.ProductRepository;

import com.example.demo.rest.exception.LogicalException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ProductDataRepository productDataRepository;
    private InfoRepository infoRepository;



    public ProductService(ProductRepository productRepository, ProductDataRepository productDataRepository,InfoRepository infoRepository) {
        this.productRepository = productRepository;
        this.productDataRepository=productDataRepository;
        this.infoRepository= infoRepository;

    }

    public ProductDTO findBestSellerForMonth(LocalDate create_date){
        Integer id=  productRepository.findBestSellerInt(create_date);
        return productDataRepository.findProductById(id).orElseThrow(new LogicalException(" не найдены покупатли за последние полгода " ));
    };

    public ProductDTO  findPrroductByUserAge( Integer age){
        Integer id= infoRepository.findProductIdByUserAge(age);
        return productDataRepository.findProductById(id).orElseThrow(new LogicalException("Нет покупателей в ворасте "+age+" лет"));
    };
    public Product  findBestSellerPlain(){
        return productRepository.findBestSellerPlain();
    };

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    public List<ProductDTO> findProductByUserId(Integer id) {
        return productDataRepository.findProductByUserId(id);
    }

    public List<ProductDTO> findAllDto() {
        return productDataRepository.findAllProductData();
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        productRepository.save(product);
        return productRepository.getOne(product.getId());
    }
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
