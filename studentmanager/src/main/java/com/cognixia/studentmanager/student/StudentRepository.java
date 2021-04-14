package com.cognixia.studentmanager.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * This will act as our Data Access Layer
 * We do with with JPA by saying @Repository
 * 
 * @author fredrickottensmeyer
 */
@Repository
public interface StudentRepository 
	extends JpaRepository<Student, Long> {

	// This is the equivalent to writing
	// SELECT * FROM student WHERE email = ?
	// Optional<Student> findStudentByEmail(String email);
	// Or we can do it this way with JDQL
	@Query("SELECT s FROM Student s WHERE s.email = ?1")
	Optional<Student> findStudentByEmail(String email);
}
