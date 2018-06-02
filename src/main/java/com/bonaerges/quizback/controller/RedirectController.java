package com.bonaerges.quizback.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.bonaerges.quizback.dto.UserDTO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.service.CourseService;
import com.bonaerges.quizback.service.UserService;

@RestController
@RequestMapping
public class RedirectController {
	@Autowired
	private UserService userService;

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "test/{id}")
	public String handleTestRequest(Model model) {

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setUrl("/question/{id}");
		RedirectAttributes redirectAttributes;

		return "redirect:/question/{id}";
	}

	@PostMapping("test/course/{id}/user")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody UserDTO post) throws NotFoundException {

		Optional<Course> courseOptional = courseService.findById(id);

		if (!courseOptional.isPresent()) {
			throw new NotFoundException("id-" + id);
		}

		Course course = courseOptional.get();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId())
				.toUri();

		return ResponseEntity.created(location).build();

	}
}
