package com.ecommerce.user.service;



import java.util.List;

import com.ecommerce.user.model.User;
import com.ecommerce.user.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(int id);

    User findOne(String userName);

    User findById(int id);

    UserDto update(UserDto userDto);
    
    UserDto findByUserName(String UserName);
}
