package com.dbg.quizback.component.mapper.course;

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


}