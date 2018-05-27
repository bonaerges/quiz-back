package com.dbg.quizback.component.mapper.user;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.UserDTO;
import com.dbg.quizback.model.User;

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
		return null;
	}

	@Override
	public List<UserDTO> modelToDto(List<User> models) {
		// TODO Auto-generated method stub
		return null;
	}


}