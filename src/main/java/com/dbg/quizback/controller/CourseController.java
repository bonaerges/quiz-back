package com.dbg.quizback.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public Set<CourseDTO>  findAll(
			@RequestParam(value = "page", defaultValue="1",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Course> course=courseService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll course count is: "+ Integer.toString(course.size()));
		return courseMapper.modelToDto(course);
	}
	
	@ResponseBody
	@RequestMapping(path="/{id}/user",method=RequestMethod.GET)
	public ResponseEntity<Set<UserDTO>> getUserCourseById(@PathVariable("id") Integer id)
			{
		Optional<Course> course=courseService.findById(id);
		ResponseEntity<Set<UserDTO>> respEnt=new ResponseEntity<Set<UserDTO>>(HttpStatus.OK);
		 if(course.isPresent()) {
				log.info("findUserByid users found "+ id);
				Set<User> usersCourse=userService.findUsersByCourse(id);
				Set<UserDTO> usersCourseList=userMapper.modelToDto(usersCourse);
		    	respEnt=new ResponseEntity<Set<UserDTO>>(usersCourseList,HttpStatus.OK);
		    }
		    else respEnt=new ResponseEntity<Set<UserDTO>>(HttpStatus.NOT_FOUND);
			return respEnt;
	}
	/************************************HTTP METHOD POST *************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CourseDTO>  create(@RequestBody CourseDTO dto){
		final Course course = courseMapper.dtoToModel(dto);
		final Course createCourse = courseService.create(course);
		return new ResponseEntity<CourseDTO>(courseMapper.modelToDto(createCourse),HttpStatus.OK);
		
	}
	

	/************************************HTTP METHOD PUT *************************************/
	
//	@ResponseBody
//	@RequestMapping(value="/{id}/user",method = {RequestMethod.PUT,RequestMethod.POST})
//	public ResponseEntity<CourseDTO> updateUserCourse(
//			@PathVariable("id") Integer id,
//			@RequestBody UserDTO dto) throws NotFoundException,DuplicatedException {
//		
//		ResponseEntity<CourseDTO> respEnt=respEntOK;
//		
//		User userModel = userMapper.dtoToModel(dto) ;
//		//Update course object
//		Optional <Course>  courseUser=courseService.findById(id);
//		if (courseUser.isPresent()) {
//			courseService.update(courseUser.get());
//			respEnt=new ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
//		}
//		else respEnt=respEntNotFound;
//		return respEnt;
//	}	
	
	/************************************HTTP METHOD DELETE *************************************/
	
	
}
