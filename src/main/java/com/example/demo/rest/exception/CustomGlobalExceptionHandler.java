package com.example.demo.rest.exception;





import com.example.demo.service.impl.EmailIsNotUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // дескриптор ошибок для @Valid
    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status, WebRequest request) {

        Map body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        // Получить все ошибки
        List errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    // Обработка Exceptions не связанных с @Valid
    @ExceptionHandler(LogicalException.class)
    protected ResponseEntity<AwesomeException> handleLogicalException(RuntimeException ex) {
        return new ResponseEntity<>(new AwesomeException(
                " ошибка : " + ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIsNotUnique.class)
    protected ResponseEntity<AwesomeException> handleEmailIsNotUnique() {
        return new ResponseEntity<>(new AwesomeException(
                "Пользователь с таким email уже существует"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PurchaseNotFound.class)
    protected ResponseEntity<AwesomeException> handleEmailIsAbsent() {
        return new ResponseEntity<>(new AwesomeException(
                "Информация о покупке не найдена"), HttpStatus.BAD_REQUEST);
    }


    @Data
    @AllArgsConstructor
    static class AwesomeException {
        private String message;
    }
}
