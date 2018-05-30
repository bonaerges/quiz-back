package com.bonaerges.quizback.component.mapper.level;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bonaerges.quizback.component.mapper.AbstractMapper;
import com.bonaerges.quizback.dto.LevelViewDTO;
import com.bonaerges.quizback.model.Level;

@Component
public class LevelMapperImpl extends AbstractMapper<Level,LevelViewDTO> implements LevelMapper {

	@Override
	public Class<? extends LevelViewDTO> dtoClazz() {
		return LevelViewDTO.class;
	}

	@Override
	public Class<? extends Level> modelClazz() {
		return Level.class;
	}

	@Override
	public List<Level> dtoToModel(List<LevelViewDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<LevelViewDTO> modelToDto(List<Level> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}