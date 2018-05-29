package com.dbg.quizback.component.mapper.level;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dbg.quizback.component.mapper.AbstractMapper;
import com.dbg.quizback.dto.LevelDTO;
import com.dbg.quizback.model.Level;

@Component
public class LevelMapperImpl extends AbstractMapper<Level,LevelDTO> implements LevelMapper {

	@Override
	public Class<? extends LevelDTO> dtoClazz() {
		return LevelDTO.class;
	}

	@Override
	public Class<? extends Level> modelClazz() {
		return Level.class;
	}

	@Override
	public List<Level> dtoToModel(List<LevelDTO> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<LevelDTO> modelToDto(List<Level> models) {
		return models.stream().map(d -> modelToDto(d)).collect(Collectors.toList());
	}


}