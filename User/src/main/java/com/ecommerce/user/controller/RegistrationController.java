package com.ecommerce.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.model.User;
import com.ecommerce.user.model.UserDto;
import com.ecommerce.user.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public User signup(@RequestBody UserDto user) {
		return userService.save(user);
	}

}
