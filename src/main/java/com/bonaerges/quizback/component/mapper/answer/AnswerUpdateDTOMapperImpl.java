package com.bonaerges.quizback.component.mapper.answer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.AnswerUpdateDTO;
import com.bonaerges.quizback.model.Answer;

@Component
public class AnswerUpdateDTOMapperImpl extends AbstractMapper<Answer,AnswerUpdateDTO> implements AnswerUpdateDTOMapper {

	@Override
	public Class<? extends AnswerUpdateDTO> dtoClazz() {
		return AnswerUpdateDTO.class;
	}

	@Override
	public Class<? extends Answer> modelClazz() {
		return Answer.class;
	}

	@Override
	public List<Answer> dtoToModel(List<AnswerUpdateDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<AnswerUpdateDTO> modelToDto(List<Answer> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}