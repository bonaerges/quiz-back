package com.bonaerges.quizback.dto;

import com.bonaerges.quizback.model.Level.LevelValue;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LevelViewDTO {

	private LevelValue name;
}
