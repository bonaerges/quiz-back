package com.bonaerges.quizback.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class RedirectController {

	  @RequestMapping(value = "test/{id}")
	    public String handleTestRequest (Model model) {

	        RedirectView rv = new RedirectView();
	        rv.setContextRelative(true);
	        rv.setUrl("/question/{id}");
	        RedirectAttributes redirectAttributes;
	       
	        return "redirect:/question/{id}";
	    }
}
