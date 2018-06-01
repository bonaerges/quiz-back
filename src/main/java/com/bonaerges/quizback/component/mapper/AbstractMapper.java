package com.bonaerges.quizback.component.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractMapper<M, D> implements Mapper<M, D> {

	@Autowired
	public DozerBeanMapper dozer;

	@Override
	public M dtoToModel(D dto) {
		return dozer.map(dto, modelClazz());
	}

	@Override
	public D modelToDto(M model) {
		return dozer.map(model, dtoClazz());
	}

	@Override
	public List<M> dtoToModel(List<D> dtos) {
		return dtos.stream().map(d -> dtoToModel(d)).collect(Collectors.toList());
	}

	@Override
	public List<D> modelToDto(List<M> models) {
		return models.stream().map(m -> modelToDto(m)).collect(Collectors.toList());
	}

}