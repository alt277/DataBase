package com.example.demo.service;


import com.example.demo.persist.entity.PurchaseInfo;
import com.example.demo.persist.entity.data.PurchaseInfoDTO;
import com.example.demo.persist.repository.InfoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InfoService {

     private InfoRepository infoRepository;


    public InfoService(InfoRepository infoRepository) {
        this.infoRepository=infoRepository;

    }

    public PurchaseInfo findById(Integer id) {
        return infoRepository.findById(id).orElseThrow();
    }

    public PurchaseInfo save(PurchaseInfoDTO dto) {
        return infoRepository.save( fromDTO(dto,new PurchaseInfo()));
    }

    public PurchaseInfo update(PurchaseInfoDTO dto) {
     PurchaseInfo  purchaseInfo = infoRepository.findById(dto.getId()).orElseThrow();
        return fromDTO(dto,purchaseInfo);
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


    }    public List<PurchaseInfo> findAllBy() {


        return infoRepository.findAllBy();
    }

    public Integer  findPId(@Param("create_date") LocalDate create_date){
        return infoRepository.findPID(create_date);
    };

    PurchaseInfo fromDTO(PurchaseInfoDTO dto, PurchaseInfo purchaseInfo) {
        purchaseInfo.setProduct_id(dto.getProduct_id());
        purchaseInfo.setUser_id(dto.getUser_id());
        purchaseInfo.setQuantity(dto.getQuantity());
        purchaseInfo.setTotalPrice(dto.getTotalPrice());
        return purchaseInfo;
    }

}
