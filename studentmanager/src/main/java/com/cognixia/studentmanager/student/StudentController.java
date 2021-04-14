package com.cognixia.studentmanager.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class will act as our API/Controller Layer
 * for Student
 * @author fredrick ottensmeyer
 */

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

	// By using @Autowired we are able to have this field
	// magically instantiated for us in the constructor
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	// Put is used for updating a resource in your system
	@PutMapping(path = "{studentId")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email){
		studentService.updateStudent(studentId, name, email);
	}
	
	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(
			@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
}
