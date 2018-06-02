package com.bonaerges.quizback.component.mapper;

import java.util.List;

public interface Mapper<M, D> {

	M dtoToModel(D dto);

	D modelToDto(M model);

	List<M> dtoToModel(List<D> dtos);

	List<D> modelToDto(List<M> models);

	Class<? extends D> dtoClazz();

	Class<? extends M> modelClazz();

}