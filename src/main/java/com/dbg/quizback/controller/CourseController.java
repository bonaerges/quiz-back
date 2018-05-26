package com.dbg.quizback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.course.CourseMapper;
import com.dbg.quizback.component.mapper.user.UserMapper;
import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.User;
import com.dbg.quizback.service.CourseService;
import com.dbg.quizback.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/course")
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseMapper courseMapper;
	
	/************************************HTTP METHOD GET *************************************/
	@RequestMapping(value = "addUserCourse/", method = RequestMethod.GET)
	public void create(@PathVariable("userId") Integer userId, @PathVariable("courseId") Integer courseId){
//	    model.addAttribute("courses", crepository.findAll());
//		model.addAttribute("student", repository.findOne(studentId));
//	    return "addStudentCourse";
		
	}
	
	@RequestMapping(value="/user/{id}/course", method=RequestMethod.GET)
	public void addUserToCourse(@PathVariable("userId") Integer userid,
	 @RequestParam("courseId") Integer courseid) {
		

		Optional<User> user = userService.findById(userid);
		if(user.isPresent()){
			Optional<Course> course=courseService.findById(courseid);
			if(course.isPresent()){
				
			}
		}
	} 
	
	/************************************HTTP METHOD POST *************************************/
	
	/************************************HTTP METHOD PUT *************************************/
	
	/************************************HTTP METHOD DELETE *************************************/
}
