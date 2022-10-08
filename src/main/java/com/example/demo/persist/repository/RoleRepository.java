package com.example.demo.persist.repository;

import com.example.demo.persist.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role,Integer>{
    Role findRoleByName (String name);
}
