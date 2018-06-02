package com.bonaerges.quizback.service;


import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
		userDAO.save(user);
		
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
		
	}
	
	@Test
	public void testFindAllOK() {
		
	}
	
	@Test
	public void testFindIdOK() {
		Mockito.when(courseDAO.findById(1)).thenReturn(Optional.of(course));
		Optional<Course> courses= courseService.findById(1);
		Assert.assertEquals(Optional.of(course),courses);
	}
	
	@Test
	public void testAddUserCourseOK() {		 
//		course.setUser(Collections.singleton(user));
//		Mockito.when(courseDAO.save(course)).thenReturn(course);
//		courseService.addUserToCourse(1, 1);
//		Assert.assertEquals(courseService.findById(1).get().getUser(),course.getUser());
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
		
	}
	
	@Test
	public void testBelongsUserToCourse() {
		
	}
}
