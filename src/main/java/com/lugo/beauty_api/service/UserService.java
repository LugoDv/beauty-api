package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.User;

import java.util.List;

public interface UserService {

    User update(User user);

    List<User> findAll();

    User findById(Long id);

    void delete(Long id);

    // UserService.java
    List<User> findAllEmployees();;
}
