package com.pearl.course.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pearl.course.been.Course;

public interface CourseService {
	List<Course> getAllCourse();
	void saveCourse(Course course);
	Course getCourseById(long id);
	void deleteCourseById(long id);
	Page<Course> findPaginated(int pageNum, int pageSize,String sortField,String sortDirection);
}
