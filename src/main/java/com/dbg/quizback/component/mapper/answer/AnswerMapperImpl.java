package com.dbg.quizback.component.mapper.answer;

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


}