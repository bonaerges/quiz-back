package com.dbg.quizback.component.mapper.answer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.AnswerDTO;
import com.dbg.quizback.model.Answer;

@Component
public class AnswerMapperImpl extends AbstractMapper<Answer,AnswerDTO> implements AnswerMapper {

	@Override
	public Class<? extends AnswerDTO> dtoClazz() {
		return AnswerDTO.class;
	}

	@Override
	public Class<? extends Answer> modelClazz() {
		return Answer.class;
	}

	@Override
	public List<Answer> dtoToModel(List<AnswerDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerDTO> modelToDto(List<Answer> models) {
		// TODO Auto-generated method stub
		return null;
	}


}