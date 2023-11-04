package com.pearl.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pearl.course.been.Course;
import com.pearl.course.been.Student;
import com.pearl.course.service.CourseService;
import com.pearl.course.service.StudentService;

import jakarta.validation.Valid;
@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	
	 @GetMapping("/course")
	    public String viewHomePage(Model model) {
	        return findPaginated(1, "courseName", "asc", model);
	    }
	 
	    @GetMapping("/course/addc")
	    public String showNewCourseForm(Model model) {
	        Course Course = new Course();
	        model.addAttribute("course", Course);
	        return "addc";
	    }
	   
	    @PostMapping("/save")
	    public String saveCourse(@Valid @ModelAttribute("course") Course course) {
	        // save Course to database
	        courseService.saveCourse(course);
	        return "redirect:/course";
	    }
	 
	    @GetMapping("/course/updatec/{id}")
	    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
	 
	        Course course = courseService.getCourseById(id);
	        model.addAttribute("course", course);
	        return "updatec";
	    }
	 
	    @GetMapping("/course/deletec/{id}")
	    public String deleteCourse(@PathVariable (value = "id") long id) {
	 
	        this.courseService.deleteCourseById(id);
	        return "redirect:/course";
	    }
	 
	    @GetMapping("/course/students")
	    public String viewStudent(Model model) {
	    	List<Student> students = studentService.getAllStudent();
	    	model.addAttribute("student", students);
	    	return "/student";
	    }
	    @GetMapping("/page/{pageNo}")
	    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
	                                @RequestParam("sortField") String sortField,
	                                @RequestParam("sortDir") String sortDir,
	                                Model model) {
	        int pageSize = 5;
	 
	        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
	        List<Course> listCourses = page.getContent();
	 
	        model.addAttribute("currentPage", pageNo);
	        model.addAttribute("totalPages", page.getTotalPages());
	        model.addAttribute("totalItems", page.getTotalElements());
	 
	        model.addAttribute("sortField", sortField);
	        model.addAttribute("sortDir", sortDir);
	        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	 
	        model.addAttribute("listCourses", listCourses);
	        return "homec";
	    }
	}
