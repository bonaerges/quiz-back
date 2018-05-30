package com.dbg.quizback.component.mapper.question;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.QuestionUpdateDTO;
import com.dbg.quizback.model.Question;

@Component
public class QuestionUpdateDTOMapperImpl extends AbstractMapper<Question,QuestionUpdateDTO> implements QuestionUpdateDTOMapper {

	@Override
	public Class<? extends QuestionUpdateDTO> dtoClazz() {
		return QuestionUpdateDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}

	@Override
	public List<Question> dtoToModel(List<QuestionUpdateDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionUpdateDTO> modelToDto(List<Question> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}