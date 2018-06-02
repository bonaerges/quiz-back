package com.bonaerges.quizback.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.servlet.view.RedirectView;

import com.bonaerges.quizback.component.mapper.course.CourseMapper;
import com.bonaerges.quizback.component.mapper.course.CoursePostDTOMapper;
import com.bonaerges.quizback.component.mapper.user.UserMapper;
import com.bonaerges.quizback.dto.CourseDTO;
import com.bonaerges.quizback.dto.CoursePostDTO;
import com.bonaerges.quizback.dto.UserCourseDTO;
import com.bonaerges.quizback.dto.UserDTO;
import com.bonaerges.quizback.exception.DuplicatedException;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.service.CourseService;

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
	CourseMapper courseMapper;
	
	@Autowired
	CoursePostDTOMapper coursePostDTOMapper;
	
	@Autowired
	UserMapper userMapper;
	
	/************************************HTTP METHOD GET *************************************/
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.FOUND)
	public List<CourseDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		List<Course> course=(List<Course>) courseService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
		log.info("findAll course count is: "+ Integer.toString(course.size()));
		return courseMapper.modelToDto(course);
	}
	
	@ResponseBody
	@RequestMapping(path="/{id}/user",method=RequestMethod.GET)
	public ResponseEntity<UserCourseDTO> getUsersCourseById(@PathVariable("id") Integer id)
			{
		Optional<Course> course=courseService.findById(id);
		ResponseEntity<UserCourseDTO> respEnt=new ResponseEntity<UserCourseDTO>(HttpStatus.OK);
		 if(course.isPresent()) {
				log.info("findUserByid users found "+ id);
				List<UserDTO> usersCourseList=userMapper.modelToDto(course.get().getUser());
				UserCourseDTO courseUser=new UserCourseDTO();
				courseUser.setUsers(usersCourseList);
				courseUser.setCourse(courseMapper.modelToDto(course.get()));
		    	respEnt=new ResponseEntity<UserCourseDTO>(courseUser,HttpStatus.OK);
		    }
		    else respEnt=new ResponseEntity<UserCourseDTO>(HttpStatus.NOT_FOUND);
			return respEnt;
	}
	

	@ResponseBody
	@RequestMapping(value="/{id}/questionnaire/{idQ}",method = {RequestMethod.GET})
	public RedirectView getQuestionnarieCourse(
			@PathVariable("id") Integer id,
			@PathVariable("idQ") Integer  idQ) throws NotFoundException,DuplicatedException {
		
	    RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/questionnaire/{idQ}");
       // RedirectAttributes redirectAttributes;
       
        return rv;
	}	
	
	@ResponseBody
	@RequestMapping(value="/{id}/questionnaire/{idQ}/result",method = {RequestMethod.GET})
	public RedirectView getResultQuestionnarieCourse(
			@PathVariable("id") Integer id,
			@PathVariable("idQ") Integer  idQ) throws NotFoundException,DuplicatedException {
		
	    RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/result/{idQ}");
       // RedirectAttributes redirectAttributes;
        return rv;
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
		courseService.addUserToCourse(id, idUser);
		return new ResponseEntity<CoursePostDTO>(HttpStatus.OK);
	}


//	@ResponseBody
//	@RequestMapping(value="/{id}/questionnaire/{idQ}",method = {RequestMethod.PUT})
//	public RedirectView updateQuestionnarieCourse(
//			@PathVariable("id") Integer id,
//			@PathVariable("idQ") Integer  idQ,RedirectAttributes redirectAttributes) throws NotFoundException,DuplicatedException {
//		
//	    RedirectView rv = new RedirectView();
//        rv.setContextRelative(false);
//        rv.setUrl("/questionnaire/{idQ}/updateOrCreate");
//        QuestionnaireDTO dto= new QuestionnaireDTO();
//        dto.setDescription("TEST");
//        dto.setIdCourse(id);
//        redirectAttributes.addFlashAttribute(dto);
//   
//       
//        return rv;
//	}	

	
	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<CourseDTO> delete(@PathVariable("id") Integer idCourse) {		
		Optional<Course> course=courseService.findById(idCourse);
		ResponseEntity<CourseDTO> respEnt=respEntOK;
		if(course.isPresent()) {
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
	@RequestMapping(path="/{id}/user/{idU}",method = RequestMethod.DELETE)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CoursePostDTO>  deleteUserCouse(@PathVariable("idU") Integer  idUser,@PathVariable("id") Integer id) throws NotFoundException  {
		ResponseEntity<CoursePostDTO> respEnt;
		courseService.deleteUserCouse(id,idUser);
		return new ResponseEntity<CoursePostDTO>(HttpStatus.NOT_FOUND);
			
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}/questionnaire/{idQ}",method = {RequestMethod.DELETE})
	public ResponseEntity<CourseDTO> deleteQuestionnarieCourse(
			@PathVariable("id") Integer id,
			@PathVariable("idQ") Integer  idQ) throws NotFoundException,DuplicatedException {
		
		ResponseEntity<CourseDTO> respEnt=respEntOK;		
		//Update course object to remove questionnarie mapped
		courseService.deleteQuestionnarieCourse(id, idQ);
		respEnt=new ResponseEntity<CourseDTO>(HttpStatus.OK);
		return respEnt;
	}	
	
}
