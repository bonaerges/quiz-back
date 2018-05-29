package com.dbg.quizback.component.mapper.result;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.ResultDTO;
import com.dbg.quizback.model.Result;

@Component
public class ResultMapperImpl extends AbstractMapper<Result,ResultDTO> implements ResultMapper {

	@Override
	public Class<? extends ResultDTO> dtoClazz() {
		return ResultDTO.class;
	}

	@Override
	public Class<? extends Result> modelClazz() {
		return Result.class;
	}

	@Override
	public List<Result> dtoToModel(List<ResultDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<ResultDTO> modelToDto(List<Result> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}