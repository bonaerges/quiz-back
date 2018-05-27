package com.dbg.quizback.component.mapper.result;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResultDTO> modelToDto(List<Result> models) {
		// TODO Auto-generated method stub
		return null;
	}


}