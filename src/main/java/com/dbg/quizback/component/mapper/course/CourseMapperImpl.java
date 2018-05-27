package com.dbg.quizback.component.mapper.course;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.CourseDTO;
import com.dbg.quizback.model.Course;

@Component
public class CourseMapperImpl extends AbstractMapper<Course,CourseDTO> implements CourseMapper {

	@Override
	public Class<? extends CourseDTO> dtoClazz() {
		return CourseDTO.class;
	}

	@Override
	public Class<? extends Course> modelClazz() {
		return Course.class;
	}

	@Override
	public List<Course> dtoToModel(List<CourseDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseDTO> modelToDto(List<Course> models) {
		// TODO Auto-generated method stub
		return null;
	}


}