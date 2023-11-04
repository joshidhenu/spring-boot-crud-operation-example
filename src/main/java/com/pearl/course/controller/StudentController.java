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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pearl.course.been.Student;
import com.pearl.course.service.StudentService;
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public String viewHomePage(Model model) {
		return findPaginated(1,"studentName", "asc", model);
	}
	@GetMapping("/student/adds")
	public String showNewStudentForm(Model model) {
		Student Student = new Student();
		model.addAttribute("student", Student);
		return "adds";
	}
	
	@PostMapping("/saves")
	public String saveStudent(@ModelAttribute("student")Student student) {
		studentService.saveStudent(student);
		return "redirect:/student";
	}
	
	@GetMapping("/student/updates/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id,Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "updates";
	}
	
	@GetMapping("/student/deletes/{id}")
	public String deleteStudent(@PathVariable(value = "id")long id) {
		this.studentService.deleteStudentById(id);
		return "redirect:/student";
	}
	
	@GetMapping("/student/page/{pageNo}")
	    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
	                                @RequestParam("sortField") String sortField,
	                                @RequestParam("sortDir") String sortDir,
	                                Model model) {
	        int pageSize = 5;
	 
	        Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
	        List<Student> listStudents = page.getContent();
	 
	        model.addAttribute("currentPage", pageNo);
	        model.addAttribute("totalPages", page.getTotalPages());
	        model.addAttribute("totalItems", page.getTotalElements());
	 
	        model.addAttribute("sortField", sortField);
	        model.addAttribute("sortDir", sortDir);
	        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	 
	        model.addAttribute("listStudents", listStudents);
	        return "homes";
	}
}
