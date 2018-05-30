package com.bonaerges.quizback.component.mapper.answer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.AnswerDTO;
import com.bonaerges.quizback.model.Answer;

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
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<AnswerDTO> modelToDto(List<Answer> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}