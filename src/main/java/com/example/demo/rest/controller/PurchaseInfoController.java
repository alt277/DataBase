package com.example.demo.rest.controller;


import com.example.demo.persist.entity.PurchaseInfo;
import com.example.demo.service.InfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Котроллер информиции о продукте",
        description = "реализует поиск  по заданным параметрам")
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
        date = date.minusDays(7);

        return infoService.findAllByDate(date);
    }

    @GetMapping(path = "/one", produces = "application/xml")
    public Integer findPID() {
        LocalDate date = LocalDate.now(); // получаем текущую дату
        date = date.minusMonths(1);

        return infoService.findProductIdByDate(date);
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