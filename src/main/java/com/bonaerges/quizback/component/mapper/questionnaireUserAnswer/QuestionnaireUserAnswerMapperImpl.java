package com.bonaerges.quizback.component.mapper.questionnaireUserAnswer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.QuestionnaireQADTO;
import com.bonaerges.quizback.model.QuestionnaireUserAnswer;

@Component
public class QuestionnaireUserAnswerMapperImpl extends AbstractMapper<QuestionnaireUserAnswer,QuestionnaireQADTO> implements QuestionnaireUserAnswerMapper {

	@Override
	public Class<? extends QuestionnaireQADTO> dtoClazz() {
		return QuestionnaireQADTO.class;
	}

	@Override
	public Class<? extends QuestionnaireUserAnswer> modelClazz() {
		return QuestionnaireUserAnswer.class;
	}

	@Override
	public List<QuestionnaireUserAnswer> dtoToModel(List<QuestionnaireQADTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionnaireQADTO> modelToDto(List<QuestionnaireUserAnswer> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}