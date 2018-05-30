package com.bonaerges.quizback.component.mapper.tag;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.TagDTO;
import com.bonaerges.quizback.model.Tag;

@Component
public class TagMapperImpl extends AbstractMapper<Tag,TagDTO> implements TagMapper {

	@Override
	public Class<? extends TagDTO> dtoClazz() {
		return TagDTO.class;
	}

	@Override
	public Class<? extends Tag> modelClazz() {
		return Tag.class;
	}

	@Override
	public List<Tag> dtoToModel(List<TagDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<TagDTO> modelToDto(List<Tag> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}