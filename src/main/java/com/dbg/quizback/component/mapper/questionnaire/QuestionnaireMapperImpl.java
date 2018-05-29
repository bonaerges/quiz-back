package com.dbg.quizback.component.mapper.questionnaire;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.QuestionnaireDTO;
import com.dbg.quizback.model.Questionnaire;

@Component
public class QuestionnaireMapperImpl extends AbstractMapper<Questionnaire,QuestionnaireDTO> implements QuestionnaireMapper {

	@Override
	public Class<? extends QuestionnaireDTO> dtoClazz() {
		return QuestionnaireDTO.class;
	}

	@Override
	public Class<? extends Questionnaire> modelClazz() {
		return Questionnaire.class;
	}

	@Override
	public List<Questionnaire> dtoToModel(List<QuestionnaireDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<QuestionnaireDTO> modelToDto(List<Questionnaire> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}