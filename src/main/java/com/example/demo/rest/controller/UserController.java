package com.example.demo.rest.controller;



import com.example.demo.persist.entity.User;
import com.example.demo.persist.entity.data.UserRequestDTO;
import com.example.demo.persist.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@Tag(name = "User resource API", description = "API to operate User resource ...")
//@CrossOrigin(origins = "http://localhost:63342")

@RequestMapping("/api/v1/user")
@RestController
public class UserController {


    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/xml")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/xml")
    public Optional findById(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

    @PostMapping(consumes = "application/xml", produces = "application/xml")
    public User createUser(@RequestBody User user) {

        return userRepository.save(user);

    }

    @PutMapping(consumes = "application/xml", produces = "application/xml")
    public User updateProduct(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.getOne(user.getId());

    }

    @DeleteMapping(path = "/delete/{id}", produces = "application/xml")
    public void deleteById(@PathVariable("id") Integer id) {

        userRepository.deleteById(id);
    }
    //вывести имя и фамилию человека совершившего больше всего покупок за полгода
    @GetMapping(path = "/findBestCustomer", produces = "application/xml")
    public UserRequestDTO findBestCustomerForHalfYear() {
        LocalDate date = LocalDate.now();
        date=date.minusMonths(6);
        return userService.findBestSellerByDate(date);

    }



    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }


}