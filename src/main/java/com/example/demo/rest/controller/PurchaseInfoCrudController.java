package com.example.demo.rest.controller;


import com.example.demo.persist.entity.PurchaseInfo;
import com.example.demo.persist.entity.data.PurchaseInfoDTO;
import com.example.demo.service.InfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//@Tag(name = "User resource API", description = "API to operate User resource ...")
//@CrossOrigin(origins = "http://localhost:63342")

@RequestMapping("/api/v1/purchase")
@RestController
public class PurchaseInfoCrudController {


    private final InfoService infoService;

    public PurchaseInfoCrudController(InfoService infoService) {

        this.infoService = infoService;
    }

    @GetMapping(path = "/{id}/id", produces = "application/xml")
    public PurchaseInfo findById(@PathVariable("id") int id) {
        return infoService.findById(id);
    }

    //    @PostMapping( consumes = "application/xml", produces = "application/xml")
//    public PurchaseInfo createProduct(@RequestBody PurchaseInfoDTO infoDTO) {
//        return infoService.save(infoDTO);
//    }
    @PostMapping(value = "/create", consumes = "application/xml", produces = "application/xml")
    public PurchaseInfo createProduct(@RequestBody PurchaseInfoDTO infoDTO) {
        return infoService.save(infoDTO);
    }

    @PutMapping(value = "/update", consumes = "application/xml", produces = "application/xml")
    public PurchaseInfo updateProduct(@RequestBody PurchaseInfoDTO infoDTO) {
        return infoService.update(infoDTO);
    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/xml")
    public void deleteById(@PathVariable("id") Integer id) {

        infoService.deleteById(id);
    }

    @GetMapping(path = "/all", produces = "application/xml")
    public List<PurchaseInfo> findAll() {
        return infoService.findAll();
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