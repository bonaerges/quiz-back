package com.dbg.quizback.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.model.User;
import com.dbg.quizback.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Set<User> findAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false)Integer size){
		return userService.findAll(PageRequest.of(page,size));
	}
	

}
