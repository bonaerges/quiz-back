package com.dbg.quizback.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.quizback.component.mapper.user.UserMapper;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.dto.UserPostDTO;
import com.dbg.quizback.model.User;
import com.dbg.quizback.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(method=RequestMethod.GET)
	public Set<UserDTO> findAll(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false)Integer size){
		
		Set<User> users=userService.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toSet());
		return userMapper.modelToDto(users);
	}
	 

	@RequestMapping(value="/user/create",method = RequestMethod.POST)
	public UserDTO create(@RequestBody UserPostDTO dto) {
		final User user = userMapper.dtoToModel(dto);
		final User createUser = userService.create(user);
		return userMapper.modelToDto(createUser);
	}

//	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
//	void update(@PathVariable("id") Integer id, @RequestBody UserPostDTO dto) {
//		
//		Optional<User> user=userService.findById(id);
//		final User userDTO = user.ifPresent(userMapper.dtoToModel(user));
//		userService.update(user);
//		
//	}
//	void delete(T t) {
//		
//	}
}
