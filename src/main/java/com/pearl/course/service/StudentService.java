package com.pearl.course.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pearl.course.been.Student;

public interface StudentService {
	List<Student> getAllStudent();
	void saveStudent (Student student);
	Student getStudentById(long id);
	void deleteStudentById(long id);
	Page<Student> findPaginated(int pageNum, int pageSize,String sortField,String sortDirection);

}
