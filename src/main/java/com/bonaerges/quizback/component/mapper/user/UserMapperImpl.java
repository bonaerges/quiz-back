package com.bonaerges.quizback.component.mapper.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.UserDTO;
import com.bonaerges.quizback.model.User;

@Component
public class UserMapperImpl extends AbstractMapper<User,UserDTO> implements UserMapper {

	@Override
	public Class<? extends UserDTO> dtoClazz() {
		return UserDTO.class;
	}

	@Override
	public Class<? extends User> modelClazz() {
		// TODO Auto-generated method stub
		return User.class;
	}

	@Override
	public List<User> dtoToModel(List<UserDTO> dtos) {
		// TODO Auto-generated method stub
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> modelToDto(List<User> models) {
		return models.stream().map(m -> modelToDto(m)).collect(Collectors.toList());
	}




}