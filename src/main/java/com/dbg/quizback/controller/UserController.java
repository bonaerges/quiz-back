package com.dbg.quizback.controller;

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

import com.dbg.quizback.component.mapper.user.UserMapper;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.dto.UserPostDTO;
import com.dbg.quizback.model.User;
import com.dbg.quizback.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/user")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
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
	
	//user/ and POST METHOD with body
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public UserDTO create(@RequestBody UserPostDTO dto) {
		final User user = userMapper.dtoToModel(dto);
		final User createUser = userService.create(user);
		return userMapper.modelToDto(createUser);
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody UserPostDTO dto) {
		
		Optional<User> user=userService.findById(id);
		ResponseEntity<?> respEnt=new ResponseEntity<>(HttpStatus.OK);
	    if(user.isPresent()) {
	    	userService.update(userMapper.dtoToModel(dto));  
	    }
	    else respEnt=new ResponseEntity(HttpStatus.NOT_FOUND);
		return respEnt;
	}

	@ResponseBody
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable("id") Integer id, @RequestBody UserPostDTO dto) {
		
		Optional<User> user=userService.findById(id);
		ResponseEntity respEnt=new ResponseEntity<Object>(HttpStatus.OK);
	    if(user.isPresent()) {
	    	userService.delete(userMapper.dtoToModel(dto));   
	    }
	    else {
	    	respEnt=new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	    }
		return respEnt;
		
		
	}
	
	
}
