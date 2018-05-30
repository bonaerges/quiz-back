package com.bonaerges.quizback.component.mapper.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.CourseDTO;
import com.bonaerges.quizback.model.Course;

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
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<CourseDTO> modelToDto(List<Course> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
		}


}