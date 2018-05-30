package com.bonaerges.quizback.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bonaerges.quizback.component.mapper.user.UserMapper;
import com.bonaerges.quizback.dto.UserDTO;
import com.bonaerges.quizback.dto.UserPostDTO;
import com.bonaerges.quizback.model.User;
import com.bonaerges.quizback.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	/************************************HTTP METHOD GET *************************************/
	//user?page=X&size=X
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public Set<UserDTO>  findAll(
			@RequestParam(value = "page", defaultValue="0",required = false) Integer page,
			@RequestParam(value = "size", defaultValue="10",required = false)Integer size){
		
		
		Set<User> users=userService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		log.info("findAll users count is: "+ Integer.toString(users.size()));
		return userMapper.modelToDto(users);
	}
	
	@ResponseBody
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id)
			{
		Optional<User> user=userService.findById(id);
		ResponseEntity<UserDTO> respEnt=new ResponseEntity<UserDTO>(HttpStatus.OK);
		 if(user.isPresent()) {
				log.info("findUserByid users found "+ id);
		    	respEnt=new ResponseEntity<UserDTO>(userMapper.modelToDto(user.get()),HttpStatus.OK);
		    }
		    else respEnt=new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
			return respEnt;
	}
	
//	@ResponseBody
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<UserDTO> getUserByMail(@RequestBody String email)
//			{
//		Optional<User> user=userService.findByEmail(email);
//		ResponseEntity<UserDTO> respEnt=new ResponseEntity<UserDTO>(HttpStatus.OK);
//		 if(user.isPresent()) {
//				log.info("findUserByMail users found by email"+ email);
//		    	respEnt=new ResponseEntity<UserDTO>(userMapper.modelToDto(user.get()),HttpStatus.OK);
//		    }
//		    else respEnt=new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
//			return respEnt;
//	}
//	
	
	/************************************HTTP METHOD POST *************************************/
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> create(@RequestBody UserPostDTO dto) {
		final User user = userMapper.dtoToModel(dto);
		final User createUser = userService.create(user);
		return new ResponseEntity<UserDTO>(userMapper.modelToDto(createUser),HttpStatus.OK);
	}

	/************************************HTTP METHOD PUT *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<UserDTO> update(@PathVariable("id") Integer id, @RequestBody UserPostDTO dto) {
		Optional<User> user=userService.findById(id);
		ResponseEntity<UserDTO> respEnt=new ResponseEntity<UserDTO>(HttpStatus.OK);
	    if(user.isPresent()) {
	    	user.get().setEmail(dto.getEmail());
	    	user.get().setName(dto.getName());
	    	user.get().setSurname(dto.getSurname());
	    	user.get().setPassword(dto.getPassword());
	    	userService.update(user.get());  
	    	respEnt=new ResponseEntity<UserDTO>(userMapper.modelToDto(user.get()),HttpStatus.OK);
	    }
	    else respEnt=new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	
	/************************************HTTP METHOD DELETE *************************************/
	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<UserDTO> delete(@PathVariable("id") Integer id) {
		Optional<User> user=userService.findById(id);
		ResponseEntity<UserDTO> respEnt=new ResponseEntity<UserDTO>(HttpStatus.OK);
	    if(user.isPresent()) {
	    	userService.delete(user.get());   
	    	respEnt=new ResponseEntity<UserDTO>(userMapper.modelToDto(user.get()),HttpStatus.OK);	    	
	    }
	    else {
	    	respEnt=new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
	    }
		return respEnt;
		
		
	}
	
	
}
