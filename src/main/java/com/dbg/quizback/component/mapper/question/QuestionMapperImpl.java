package com.dbg.quizback.component.mapper.question;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.QuestionDTO;
import com.dbg.quizback.model.Question;

@Component
public class QuestionMapperImpl extends AbstractMapper<Question,QuestionDTO> implements QuestionMapper {

	@Override
	public Class<? extends QuestionDTO> dtoClazz() {
		return QuestionDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}

	@Override
	public List<Question> dtoToModel(List<QuestionDTO> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionDTO> modelToDto(List<Question> models) {
		// TODO Auto-generated method stub
		return null;
	}


}