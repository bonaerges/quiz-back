package com.dbg.quizback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dbg.quizback.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

}
