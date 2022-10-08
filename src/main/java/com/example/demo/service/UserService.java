package com.example.demo.service;


import com.example.demo.persist.entity.User;
import com.example.demo.persist.entity.data.UserRequestDTO;
import com.example.demo.rest.exception.EmailIsAbsent;

import java.util.List;

public interface UserService {

    User createUser(UserRequestDTO user);

    void deleteUser(String email) throws EmailIsAbsent;

    User updateUser(UserRequestDTO user);

    List<User> showUsers();

    User buildUser(UserRequestDTO userRequestDTO);

    void authenticateUser(User user);

    User getUser(String email);

    UserRequestDTO getUserDTO(String email);


}
