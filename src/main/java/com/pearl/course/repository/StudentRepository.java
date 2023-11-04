package com.pearl.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pearl.course.been.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{


}
