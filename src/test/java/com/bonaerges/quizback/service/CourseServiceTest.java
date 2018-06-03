package com.bonaerges.quizback.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bonaerges.quizback.dao.CourseDAO;
import com.bonaerges.quizback.dao.UserDAO;
import com.bonaerges.quizback.model.Course;
import com.bonaerges.quizback.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

	@InjectMocks
	CourseService courseService=new CourseServiceImpl();

	@Mock
	UserService userService;
	
	@Mock
	private Course course;

	@Mock
	private User user;
	
	@Mock
	private CourseDAO courseDAO;

	@Mock
	private UserDAO userDAO;


			
		

	@Before
    public void setUp() throws Exception {
		course=new Course();
		course.setId(1);
		course.setDescription("Spring");
		String starDate = "2018-05-14";
		SimpleDateFormat  formatter = new SimpleDateFormat("YYYY-MM-DD");
		Date date = formatter.parse(starDate);
		course.setStartDate(date);
		String endDate = "2018-05-31";
		date = formatter.parse(endDate);
		course.setFinishDate(date);
		String createdOn="2018-05-13";
		date = formatter.parse(createdOn);
		course.setCreatedOn(date);
		user= new User();
		user.setEmail("test1@tets.com");
		user.setId(1);
		user.setName("TEST1");
		user.setSurname("SURNAMETEST");		
		//user.setCourse(Collections.singletonList(course));
		//course.setUser(Collections.singletonList(user));
		
		
    }

	
	@Test
	public void testMockCreation() {
		assertNotNull(courseService);
		assertNotNull(courseDAO);
		assertNotNull(course);
	}
	
	@Test
	public void testCreateOK() {
		Mockito.when(courseDAO.save(course)).thenReturn(course);
		Course courseNew=courseService.create(course);
		assertNotNull(courseNew);
		Assert.assertEquals(courseNew,course);
	}
	
	@Test
	public void testUpdateOK() throws Exception {
		
		course.setDescription("New Course");
		Mockito.when(courseDAO.save(course)).thenReturn(course);
		Course courseUpdate=courseService.create(course);
		courseUpdate.setDescription("New Course");
		courseService.update(courseUpdate);
		Optional<Course> courseUpdateS= courseService.findById(courseUpdate.getId());
		courseService.update(courseUpdateS.get());
		assertNotNull(courseUpdateS);
		assertNotNull(courseUpdateS);
		//Mockito.verify(courseService.update(courseUpdateS.get()).update(courseUpdate));
		Assert.assertEquals(course.getDescription(),courseUpdateS.get().getDescription());
	}
	
	@Test
	public void testDeleteOK() {
		courseService.delete(course);
		Assert.assertNull(courseService.findById(1));
		
	}
	
	@Test
	public void testFindAllOK() {
		List<Course> courses=courseService.findAll(PageRequest.of(0, 10));
		ArrayList<Course> q = new ArrayList<>();
		q.add(course);
		q.add(new Course());
		Mockito.when(courseDAO.findAll(PageRequest.of(0, 10))).thenReturn((Page<Course>) q);

		List<Course> result = courseService.findAll(PageRequest.of(0, 10));

		verify(courseService, times(1)).findAll(PageRequest.of(0, 10));
		assertEquals(2, result.size());
		Assert.assertNotNull(courses);
	}
	
	@Test
	public void testFindIdOK() {
		Mockito.when(courseDAO.findById(1)).thenReturn(Optional.of(course));
		Optional<Course> courses= courseService.findById(1);
		Assert.assertEquals(Optional.of(course),courses);
	}
	
	@Test
	public void testAddUserCourseOK() {		 
		course.setUser(Collections.singletonList(user));
		Mockito.when(courseDAO.save(course)).thenReturn(course);
	
		List <User> users=courseService.addUserToCourse(1, 1);
		Assert.assertEquals(users,course.getUser());
	}
	
	@Test
	public void testfindAllCourses() {
//		List<Course> courses = new ArrayList<>();
//		Page<Course> pagedResponse = new PageImpl(courses);
//		Mockito.when(courseDAO.findAll(any(Predicate.class))).thenReturn(pagedResponse);
//		  Mockito.when(courseDAO.findAll(Pageable.unpaged())).thenReturn(new PageImpl<>(new ArrayList<Course>()));
//		  List<Course> courses = courseService.findAll(Pageable.unpaged());
//
//		  Mockito.verify(courseDAO).findAll(page);
//		  Assert.assertEquals(0, courses.size());	
		}
	@Test
	public void testDeleteUserFromCourse() {
		course.getUser().removeIf(u -> u.getId() == 1);
		Mockito.when(courseDAO.save(course)).thenReturn(course);		
		courseService.deleteUserCouse(1, 1);
		Optional<Course> courses= courseService.findById(1);
		Assert.assertTrue(courses.get().getUser().stream().noneMatch(u -> u.getId() == 1));
		Assert.assertTrue(course.getUser().stream().noneMatch(u -> u.getId() == 1));
		Assert.assertEquals(course, courses);
		
	}
	
	@Test
	public void testBelongsUserToCourse() {
		boolean belongsCourse=courseService.belongsUserToCourse(1, 1);
		Assert.assertTrue(belongsCourse == true);
	}
	
}
