package com.pearl.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pearl.course.been.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
