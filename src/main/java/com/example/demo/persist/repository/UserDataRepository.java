package com.example.demo.persist.repository;


import com.example.demo.persist.entity.User;

import com.example.demo.persist.entity.data.UserRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<User, Integer> {

    @Query("select new com.example.demo.persist.entity.data.UserRequestDTO( u.email,u.name,u.lastName,u.age) from User u " +
            " where u.id = :id  ")
    Optional <UserRequestDTO> findUserById (@Param("id") Integer id);
}
