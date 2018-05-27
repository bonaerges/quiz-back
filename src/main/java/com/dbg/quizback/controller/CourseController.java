package com.dbg.quizback.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.course.CourseMapper;
import com.dbg.quizback.component.mapper.user.UserMapper;
import com.dbg.quizback.dto.AnswerDTO;
import com.dbg.quizback.dto.CourseDTO;
import com.dbg.quizback.dto.QuestionDTO;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.dto.UserPostDTO;
import com.dbg.quizback.exception.DuplicatedException;
import com.dbg.quizback.exception.NotFoundException;
import com.dbg.quizback.model.Answer;
import com.dbg.quizback.model.Course;
import com.dbg.quizback.model.Question;
import com.dbg.quizback.model.User;
import com.dbg.quizback.service.CourseService;
import com.dbg.quizback.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/course")
public class CourseController {


	final ResponseEntity<CourseDTO> respEntOK=new ResponseEntity<CourseDTO>(HttpStatus.OK);
	final ResponseEntity<CourseDTO> respEntNotFound=new ResponseEntity<CourseDTO>(HttpStatus.NOT_FOUND);
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CourseMapper courseMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/************************************HTTP METHOD GET *************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<CourseDTO>  create(@PathVariable("id") Integer id,@RequestBody CourseDTO dto){
		final Course course = courseMapper.dtoToModel(dto);
		final Course createCourse = courseService.create(course);
		return new ResponseEntity<CourseDTO>(courseMapper.modelToDto(createCourse),HttpStatus.OK);
		
	}
	
	/************************************HTTP METHOD POST *************************************/
	

	/************************************HTTP METHOD PUT *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}/user",method = {RequestMethod.PUT,RequestMethod.POST})
	public ResponseEntity<CourseDTO> updateUserCourse(
			@PathVariable("id") Integer id,
			@RequestBody UserDTO dto) throws NotFoundException,DuplicatedException {
		
		ResponseEntity<CourseDTO> respEnt=respEntOK;
		
		User userModel = userMapper.dtoToModel(dto) ;
		//For idCourse  save new user, then, update course with given user
		userService.addUserCourse(userModel,id);	
		//Update course object
		Optional <Course>  courseUser=userService.findCourseByEmail(userModel.getEmail(),id);
		if (courseUser.isPresent()) {
			courseService.update(courseUser.get());
			respEnt=new ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}	
	
	/************************************HTTP METHOD DELETE *************************************/
	
	
}
