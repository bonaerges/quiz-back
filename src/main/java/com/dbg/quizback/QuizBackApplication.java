package com.dbg.quizback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class QuizBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizBackApplication.class, args);
	}

}
