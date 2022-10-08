package com.example.demo.rest.controller;


import com.example.demo.persist.entity.Product;
import com.example.demo.persist.entity.PurchaseInfo;
import com.example.demo.persist.entity.data.ProductDTO;
import com.example.demo.persist.entity.data.PurchaseInfoDTO;
import com.example.demo.persist.repository.InfoRepository;
import com.example.demo.persist.repository.ProductRepository;
import com.example.demo.service.InfoService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Tag(name = "User resource API", description = "API to operate User resource ...")
//@CrossOrigin(origins = "http://localhost:63342")

@RequestMapping("/api/v1/purchase/info")
@RestController
public class PurchaseInfoController {


    private final InfoService infoService;

    public PurchaseInfoController(InfoService infoService) {

        this.infoService = infoService;
    }

    @GetMapping(path = "/{id}/id", produces = "application/xml")
    public PurchaseInfo findById(@PathVariable("id") int id) {
        return infoService.findById(id);
    }


//вывести списрк поеупок за последнюю неделю
    @GetMapping(path = "/week", produces = "application/xml")
    public List<PurchaseInfo> findAllByDate() {
        LocalDate date = LocalDate.now(); // получаем текущую дату
        date=date.minusDays(7);
        System.out.println("Дата минус неделя =" +date);
        return infoService.findAllByDate(date);
    }

    @GetMapping(path = "/one", produces = "application/xml")
    public Integer findPID() {
        LocalDate date = LocalDate.now(); // получаем текущую дату
        date=date.minusMonths(1);
        System.out.println("Дата минус день =" +date);
        System.out.println("Результат = " + infoService.findPId(date));
        return infoService.findPId(date);
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