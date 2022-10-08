package com.example.demo.service;


import com.example.demo.persist.entity.PurchaseInfo;
import com.example.demo.persist.entity.data.PurchaseInfoDTO;
import com.example.demo.persist.repository.InfoRepository;
import com.example.demo.rest.exception.LogicalException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InfoService {

    private InfoRepository infoRepository;
    private ProductService productService;


    public InfoService(InfoRepository infoRepository, ProductService productService) {
        this.infoRepository = infoRepository;
        this.productService = productService;
    }

    public PurchaseInfo findById(Integer id) {
        return infoRepository.findById(id).orElseThrow();
    }

    public PurchaseInfo save(PurchaseInfoDTO dto) {
        validatePurchase(dto);
        return infoRepository.save(fromDTO(dto, new PurchaseInfo()));
    }

    public PurchaseInfo update(PurchaseInfoDTO dto) {
        validatePurchase(dto);
        PurchaseInfo purchaseInfo = infoRepository.findById(dto.getId()).orElseThrow();
        return infoRepository.save(fromDTO(dto, purchaseInfo));
    }

    public void deleteById(Integer id) {
        infoRepository.deleteById(id);
    }

    public List<PurchaseInfo> findAll() {
        return infoRepository.findAll();
    }

    public void remove(Integer id) {
        infoRepository.deleteById(id);
    }

    public List<PurchaseInfo> findAllByDate(LocalDate date) {
        return infoRepository.findAllByCreateDate(date);
    }

    public List<PurchaseInfo> findAllBy() {
        return infoRepository.findAllBy();
    }

    public Integer findProductIdByDate(@Param("create_date") LocalDate create_date) {
        return infoRepository.findProductIdByDate(create_date);
    }

    PurchaseInfo fromDTO(PurchaseInfoDTO dto, PurchaseInfo purchaseInfo) {
        purchaseInfo.setProduct_id(dto.getProduct_id());
        purchaseInfo.setUser_id(dto.getUser_id());
        purchaseInfo.setQuantity(dto.getQuantity());
        purchaseInfo.setTotalPrice(dto.getTotalPrice());
        Integer id = purchaseInfo.getProduct_id();
        Double price = productService.findById(id).orElseThrow().getPrice();
        purchaseInfo.setTotalPrice(dto.getQuantity() * price);
        return purchaseInfo;
    }

    void validatePurchase(PurchaseInfoDTO dto) {
        if (dto.getUser_id() == null) {
            System.out.println("Ноль юзер: " + dto.getUser_id());
            throw new LogicalException("Пользователь незарегистрирован");
        }

        if (dto.getProduct_id() == null) {
            System.out.println("Ноль продукт:" + dto.getProduct_id());
            throw new LogicalException("Продукт отсутствует ");
        }
        if (dto.getQuantity() == null) {
            System.out.println("Ноль количество:" + dto.getQuantity());
            throw new LogicalException("Введите количество товара ");
        }
    }
}
