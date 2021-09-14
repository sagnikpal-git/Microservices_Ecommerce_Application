package com.ecommerce.user.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.user.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUserName(String userName);
}
