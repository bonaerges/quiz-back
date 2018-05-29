package com.dbg.quizback.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import com.dbg.quizback.dao.CourseDAO;
import com.dbg.quizback.model.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

	@InjectMocks
	CourseService courseService=new CourseServiceImpl();

	
	@Mock
	private Course course;

	@Mock
	private CourseDAO courseDAO;

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
    }
	
	@Test
	public void testMockCreation() {
		assertNotNull(courseService);
		assertNotNull(courseDAO);
		assertNotNull(course);
	}
	
	
	@Test
	public void testFindAllCourseOK() {
		
		int page=0;
		int size=10;
		Mockito.when(courseDAO.findById(1)).thenReturn(Optional.of(course));
		Optional<Course> courses= courseService.findById(1);
		Assert.assertEquals(Optional.of(course),courses);
	}
}
