package com.cognixia.studentmanager.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class will act as the service layer
 * handling the business logic for student
 * 
 * @author fredrickottensmeyer
 */


// In order for our app to work, this service class must be a 
// Spring Bean.  We do this by using the @Service annotation

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository
				.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		studentRepository.save(student);
	}
	
	// When we use @Transactional the entity goes into
	// a managed state
	@Transactional
	public void updateStudent(Long studentId, 
							  String name, 
							  String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException(
						"student with id " + studentId + "does not exist"));
		
		if (name != null && 
				name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null &&
				email.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository
					.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
	}
	
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException(
					"Student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);
	}

	
}
