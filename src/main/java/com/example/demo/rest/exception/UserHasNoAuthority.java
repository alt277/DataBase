package com.example.demo.rest.exception;

public class UserHasNoAuthority extends RuntimeException {
    public UserHasNoAuthority() {
        super("У пользователя нет необходимых прав доступа");
    }
}
