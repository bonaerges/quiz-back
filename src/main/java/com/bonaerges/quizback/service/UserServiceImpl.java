package com.bonaerges.quizback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.exception.DuplicatedException;
import com.bonaerges.quizback.exception.NotFoundException;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Autowired
	CourseService courseService;

	@Autowired
	DozerBeanMapper dozerBean;

	@Override
	@ExceptionHandler({ ConstraintViolationException.class })
	public User create(User t) {

		User userObject = userDAO.save(t);
		log.info(" User create successfully " + t.toString());
		return userObject;
	}

	@Override
	public void update(User t) {
		userDAO.save(t);
		log.info(" User update successfully " + t.toString());

	}

	@Override
	public void delete(User t) {
		userDAO.delete(t);
		log.info(" User delete successfully " + t.toString());

	}

	@Override
	public Optional<User> findById(Integer id) {
		Optional<User> userObject = userDAO.findById(id);
		log.info(" User findById successfully " + userObject.toString());
		return userObject;
	}

	@Override
	public List<User> findAll(Pageable p) {
		int page = p.getPageNumber();
		int size = p.getPageSize();
		return userDAO.findAll(PageRequest.of(page, size)).stream().collect(Collectors.toList());
	}

	public Optional<User> findOneByNameOrderByIdDesc(String name) {
		Optional<User> userObject = userDAO.findOneByNameOrderByIdDesc(name);
		userObject.ifPresent(u -> log.info("User findOneByNameOrderByIdDesc " + u.toString()));
		return userObject;

	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	/**
	 * 
	 */
	public void addUserCourse(User userModel, Integer idCourse) throws NotFoundException, DuplicatedException {
		Optional<Course> courseObject = courseService.findById(idCourse);
		boolean belongsCourse = false;
		if (courseObject.isPresent()) {
			belongsCourse = isUserOnCourse(idCourse, userModel.getEmail());
			if (belongsCourse)
				log.warn(" User " + userModel.getId() + " already was included into course " + idCourse);
			else {
				userModel.getCourse().add(courseObject.get());
				userDAO.save(userModel);
				courseService.update(courseObject.get());
				log.info(" Add User " + userModel.getId() + " to course " + idCourse + " successfully ");
			}
		}

	}

	/***********
	 * VALIDATIONS METHODS
	 **************************************************/
	/**
	 * 
	 * @param idCourse
	 * @param email
	 * @return
	 * @throws NotFoundException
	 * @throws DuplicatedException
	 */
	private boolean isUserOnCourse(final Integer idCourse, final String email)
			throws NotFoundException, DuplicatedException {
		Optional<User> user = userDAO.findByEmail(email);
		boolean isOnCourse = true;
		if (user.isPresent()) {
			Optional<Course> courseUser = user.get().getCourse().stream().filter(course -> course.getId() == idCourse)
					.findFirst();
			if (courseUser.isPresent())
				throw new DuplicatedException(
						"Course with ID: '" + idCourse + "' does not contain user  with mail: '" + email + "'");
			else {
				isOnCourse = false;
			}
		} else
			throw new NotFoundException("User with mail: '" + email + "' Not FOUND");

		return isOnCourse;

	}

}
