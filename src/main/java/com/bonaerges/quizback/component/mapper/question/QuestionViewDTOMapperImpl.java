package com.bonaerges.quizback.component.mapper.question;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.QuestionViewDTO;
import com.bonaerges.quizback.model.Question;

@Component
public class QuestionViewDTOMapperImpl extends AbstractMapper<Question,QuestionViewDTO> implements QuestionViewDTOMapper {

	@Override
	public Class<? extends QuestionViewDTO> dtoClazz() {
		return QuestionViewDTO.class;
	}

	@Override
	public Class<? extends Question> modelClazz() {
		return Question.class;
	}

	@Override
	public List<Question> dtoToModel(List<QuestionViewDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionViewDTO> modelToDto(List<Question> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}