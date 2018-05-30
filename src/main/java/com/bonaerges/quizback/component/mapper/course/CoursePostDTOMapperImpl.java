package com.bonaerges.quizback.component.mapper.course;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.CoursePostDTO;
import com.bonaerges.quizback.model.Course;

@Component
public class CoursePostDTOMapperImpl extends AbstractMapper<Course,CoursePostDTO> implements CoursePostDTOMapper {

	@Override
	public Class<? extends CoursePostDTO> dtoClazz() {
		return CoursePostDTO.class;
	}

	@Override
	public Class<? extends Course> modelClazz() {
		return Course.class;
	}

	@Override
	public List<Course> dtoToModel(List<CoursePostDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<CoursePostDTO> modelToDto(List<Course> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
		}


}