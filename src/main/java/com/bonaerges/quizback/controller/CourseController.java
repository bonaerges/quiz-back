package com.bonaerges.quizback.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.course.CourseMapper;
import com.bonaerges.quizback.component.mapper.course.CoursePostDTOMapper;
import com.bonaerges.quizback.component.mapper.user.UserMapper;
import com.bonaerges.quizback.dto.CourseDTO;
import com.bonaerges.quizback.dto.CoursePostDTO;
import com.bonaerges.quizback.dto.UserDTO;
import com.bonaerges.quizback.exception.DuplicatedException;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Questionnaire;
import com.bonaerges.quizback.model.User;
import com.bonaerges.quizback.service.CourseService;
import com.bonaerges.quizback.service.QuestionnaireService;
import com.bonaerges.quizback.service.UserService;

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
	QuestionnaireService questionnaireService;
	
	@Autowired
	CourseMapper courseMapper;
	
	@Autowired
	CoursePostDTOMapper coursePostDTOMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/************************************HTTP METHOD GET *************************************/
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public Set<CourseDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		Set<Course> course=courseService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll course count is: "+ Integer.toString(course.size()));
		return courseMapper.modelToDto(course);
	}
	
	@ResponseBody
	@RequestMapping(path="/{id}/",method=RequestMethod.GET)
	public ResponseEntity<Set<UserDTO>> getUserCourseById(@PathVariable("id") Integer id)
			{
		Optional<Course> course=courseService.findById(id);
		ResponseEntity<Set<UserDTO>> respEnt=new ResponseEntity<Set<UserDTO>>(HttpStatus.OK);
		 if(course.isPresent()) {
				log.info("findUserByid users found "+ id);
				Set<UserDTO> usersCourseList=userMapper.modelToDto(course.get().getUser());
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
	@ResponseBody	
	@RequestMapping(path="/{id}/user/{idU}",method = RequestMethod.PUT)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CoursePostDTO>  addUserCouse(@PathVariable("idU") Integer  idUser,@PathVariable("id") Integer id) throws NotFoundException  {
		ResponseEntity<CoursePostDTO> respEnt;
		Optional <Course>  courseUser=courseService.findById(id);
		if (courseUser.isPresent()) {
				Optional <User> user=userService.findById(idUser);
				if (!courseService.belongsUserToCourse(id, idUser)) {
					courseUser.get().getUser().add(user.get());
					courseService.update(courseUser.get());
					respEnt=new ResponseEntity<CoursePostDTO>(coursePostDTOMapper.modelToDto(courseUser.get()),HttpStatus.OK);
				}
				else throw new NotFoundException("User with id: "+ idUser + " already belong to course id: "+ id );
		}
		else return new ResponseEntity<CoursePostDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
			
		
	}

	
	@ResponseBody
	@RequestMapping(value="/{id}/questionnaire/{idQ}",method = {RequestMethod.PUT})
	public ResponseEntity<CourseDTO> addQuestionnarieCourse(
			@PathVariable("id") Integer id,
			@PathVariable("idQ") Integer  idQ) throws NotFoundException,DuplicatedException {
		
		ResponseEntity<CourseDTO> respEnt=respEntOK;
		
		//Update course object
		Optional <Course>  courseUser=courseService.findById(id);
		final Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		if (courseUser.isPresent() && questionnaireModel.isPresent()) {
			courseUser.get().getQuestionnaire().add(questionnaireModel.get());
			courseService.update(courseUser.get());
			respEnt=new ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}	
	
	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<CourseDTO> delete(@PathVariable("id") Integer idCourse) {		
		Optional<Course> course=courseService.findById(idCourse);
		ResponseEntity<CourseDTO> respEnt=respEntOK;
		if(course.isPresent()) {
			//delete all answers linked to current question 
			course.get().getQuestionnaire().forEach((final Questionnaire questLink)->questionnaireService.delete(questLink));
			courseService.delete(course.get());   
			respEnt=new ResponseEntity<CourseDTO>(HttpStatus.OK);
			log.info("Succesfully delete course " + idCourse + " and questioannaries linked to course"); 
		}
		else {
			respEnt=new ResponseEntity<CourseDTO>(HttpStatus.NOT_FOUND);
		}
		return respEnt;
	}
	
	@ResponseBody	
	@RequestMapping(path="/{id}/user/{idU}",method = RequestMethod.PUT)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CoursePostDTO>  deleteUserCouse(@PathVariable("idU") Integer  idUser,@PathVariable("id") Integer id) throws NotFoundException  {
		ResponseEntity<CoursePostDTO> respEnt;
		Optional <Course>  courseUser=courseService.findById(id);
		if (courseUser.isPresent()) {
				Optional <User> user=userService.findById(idUser);
				if (courseService.belongsUserToCourse(id, idUser)) {
					courseUser.get().getUser().remove(user.get());
					courseService.update(courseUser.get());
					respEnt=new ResponseEntity<CoursePostDTO>(coursePostDTOMapper.modelToDto(courseUser.get()),HttpStatus.OK);
				}
				else throw new NotFoundException("User with id: "+ idUser + " does ot belong to course id: "+ id );
		}
		else return new ResponseEntity<CoursePostDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/questionnaire/{idQ}",method = {RequestMethod.PUT})
	public ResponseEntity<CourseDTO> deleteQuestionnarieCourse(
			@PathVariable("id") Integer id,
			@PathVariable("idQ") Integer  idQ) throws NotFoundException,DuplicatedException {
		
		ResponseEntity<CourseDTO> respEnt=respEntOK;
		
		//Update course object to remove questionnarie mapped
		Optional <Course>  courseUser=courseService.findById(id);
		final Optional<Questionnaire> questionnaireModel=questionnaireService.findById(id);
		if (courseUser.isPresent() && questionnaireModel.isPresent()) {
			courseUser.get().getQuestionnaire().remove(questionnaireModel.get());
			courseService.update(courseUser.get());
			respEnt=new ResponseEntity<CourseDTO>(courseMapper.modelToDto(courseUser.get()),HttpStatus.OK);
		}
		else respEnt=respEntNotFound;
		return respEnt;
	}	
	
}
