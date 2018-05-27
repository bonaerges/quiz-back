package com.dbg.quizback.component.mapper.level;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LevelDTO> modelToDto(List<Level> models) {
		// TODO Auto-generated method stub
		return null;
	}


}