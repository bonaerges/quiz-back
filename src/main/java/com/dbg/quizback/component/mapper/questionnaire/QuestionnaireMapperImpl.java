package com.dbg.quizback.component.mapper.questionnaire;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionnaireDTO> modelToDto(List<Questionnaire> models) {
		// TODO Auto-generated method stub
		return null;
	}


}