package com.dbg.quizback.component.mapper.question;

import java.util.List;
import java.util.stream.Collectors;

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
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionDTO> modelToDto(List<Question> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}