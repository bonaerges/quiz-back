package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bonaerges.quizback.dao.QuestionnaireDAO;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.Question;
import com.bonaerges.quizback.model.Questionnaire;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionnaireServiceImpl implements QuestionnaireService {

	@Autowired
	QuestionnaireDAO questionnaireDAO;

	@Autowired
	ResultService resultService;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	@Autowired
	QuestionService questionService;

	@Override
	public Questionnaire create(Questionnaire t) {
		Questionnaire questionnaireObject = questionnaireDAO.save(t);
		log.info(" Questionnaire create successfully " + t.toString());
		return questionnaireObject;
	}

	@Override
	public void update(Questionnaire t) {
		questionnaireDAO.save(t);
		log.info(" Questionnaire update successfully " + t.toString());

	}

	@Override
	public void delete(Questionnaire t) {
		// Delete questions linked to questionnaire
		t.getQuestion().forEach(question -> questionService.delete(question));
		questionnaireDAO.delete(t);
		log.info(" Questionnaire delete successfully " + t.toString());

	}

	@Override
	public Optional<Questionnaire> findById(Integer id) {
		Optional<Questionnaire> questionnaireObject = questionnaireDAO.findById(id);
		log.info(" Questionnaire findById successfully " + questionnaireObject.toString());
		return questionnaireObject;
	}

	@Override
	public List<Questionnaire> findAll(Pageable p) {
		int page = p.getPageNumber();
		int size = p.getPageSize();
		return questionnaireDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}

	public Optional<Questionnaire> findOneByDescriptionOrderByIdDesc(String name) {
		Optional<Questionnaire> questionnaireObject = questionnaireDAO.findOneByDescriptionOrderByIdDesc(name);
		questionnaireObject.ifPresent(q -> log.info("Questionnaire findOneByDescriptionOrderByIdDesc " + q.toString()));
		return questionnaireObject;

	}
	/**
	 * IF QUESTIONNAIRE DOES NOT EXIST IT IS ALLOWED TO CREATE IT WHEN COURSE IS
	 * TRYING TO ADD A NEW QUESTIONNAIRE TO COURSE
	 */
	public void addQuestionnarieCourse(Integer idCourse, Integer idQuestionnaire) throws NotFoundException {
		Optional<Course> courseUser = courseService.findById(idCourse);
		if (courseUser.isPresent()) {
			courseUser.get().getQuestionnaire().forEach(quest -> {
				if (quest.getId() == idQuestionnaire) {
					log.error("Questionaire id " + idQuestionnaire + " already linked to course id " + idCourse);
				} else {
					Optional<Questionnaire> questCourse = questionnaireDAO.findById(idQuestionnaire);
					if (questCourse.isPresent()) {
						courseUser.get().getQuestionnaire().add(questCourse.get());
						courseService.update(courseUser.get());
					} else {
						log.warn("Questionaire id " + idQuestionnaire
								+ " not created so has been created and lined to course id " + idCourse);
						questCourse.get().setCourse(courseUser.get());
						questionnaireDAO.save(questCourse.get());
					}
				}
			});
		} else
			throw new NotFoundException("");

	}

	/**
	 * LINK Existing question to questionnaire
	 */
	public void linkQuestionnarieQuestion(Integer idQuestion, Integer idQuestionnaire) throws NotFoundException {
		Optional<Question> question = questionService.findById(idQuestion);
		Optional<Questionnaire> questionnaire = questionnaireDAO.findById(idQuestionnaire);
		if (question.isPresent() && questionnaire.isPresent()) {
			questionnaire.get().getQuestion().add(question.get());
			questionnaireDAO.save(questionnaire.get());
		} else
			throw new NotFoundException("Question " + idQuestion + " or Questionnaire " + idQuestionnaire + " NOT FOUND!!!");
	}
	@Override
	/**
	 * 
	 */
	public Optional<Course> getCourse(Integer idCourse) throws NotFoundException {
		Optional<Course> courseUser = courseService.findById(idCourse);	
		return courseUser;
	}
	


}
