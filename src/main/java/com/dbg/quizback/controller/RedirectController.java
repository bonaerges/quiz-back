package com.dbg.quizback.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
public class RedirectController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
		public String viewRegistrationPage() {
	            return "redirect:/user";
	        }
}
