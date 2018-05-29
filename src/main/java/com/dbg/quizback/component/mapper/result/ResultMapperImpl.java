package com.dbg.quizback.component.mapper.result;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.ResultViewDTO;
import com.dbg.quizback.model.Result;

@Component
public class ResultMapperImpl extends AbstractMapper<Result,ResultViewDTO> implements ResultMapper {

	@Override
	public Class<? extends ResultViewDTO> dtoClazz() {
		return ResultViewDTO.class;
	}

	@Override
	public Class<? extends Result> modelClazz() {
		return Result.class;
	}

	@Override
	public List<Result> dtoToModel(List<ResultViewDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<ResultViewDTO> modelToDto(List<Result> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}