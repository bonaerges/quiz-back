package com.dbg.quizback.component.mapper.tag;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.TagDTO;
import com.dbg.quizback.model.Tag;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TagDTO> modelToDto(List<Tag> models) {
		// TODO Auto-generated method stub
		return null;
	}


}