package com.dbg.quizback.component.mapper.tag;

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


}