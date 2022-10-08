package com.example.demo.persist.entity.data;

import com.example.demo.persist.entity.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
public class UserRequestDTO {

    @Email
    private String email;

//    @NotNull(message = "Пароль не может быть null")
//    @Size(min = 8, max = 100, message
//            = "Пароль должен быть от 8 символов")
//    private String password;

    @NotNull(message = "Имя не может быть null")
    @Size(min = 1, max = 50, message
            = "Имя должно быть от 1 символа")
    private String name;

    @NotNull(message = "Фамилия не может быть null")
    @Size(min = 1, max = 50, message
            = "Фамилия должна быть от 1 символа")
    private String lastName;

    private Integer age;

    private List <String > roles;
    private List <Role> roleList;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public UserRequestDTO(String email, String name, String lastName, Integer age) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public UserRequestDTO(String email, String name, String lastName, Integer age, List<String> roles, List<Role> roleList) {
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
        this.roleList = roleList;
    }
}
