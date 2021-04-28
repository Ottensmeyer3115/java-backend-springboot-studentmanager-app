package student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cognixia.studentmanager.student.Student;
import com.cognixia.studentmanager.student.StudentRepository;
import com.cognixia.studentmanager.student.StudentService;
import com.cognixia.studentmanager.student.StudentController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;


public class StudentRegistrationService {

	@Mock
    private StudentRepository studentRepository;

	
	@Captor
	private ArgumentCaptor<Student> studentArgumentCaptor;

	private StudentService underTest;
	    
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	    underTest = new StudentService(studentRepository);
	}
	
	@Test
    void itShouldSaveNewStudent() {
        // Given a Student and an email
		String email = "kermitthefrog@gmail.com";
        Student student = new Student("kermit", email, LocalDate.of(2000, Month.JANUARY, 5));

        // ... a request
        StudentService request = new StudentService(studentRepository);

        // ... No Student with email passed
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.empty());


        // When
        underTest.addNewStudent(student);

        // Then
        then(studentRepository).should().save(studentArgumentCaptor.capture());
        Student studentArgumentCaptorValue = studentArgumentCaptor.getValue();
        assertThat(studentArgumentCaptorValue).isEqualTo(student);
    }
	
	@Test
    void itShouldNotSaveStudentWhenStudentExists() {
        // Given a student
        String email = "donald.duck@gmail.com";
        Student student = new Student("don", email, LocalDate.of(2000, Month.JANUARY, 5));

        // ... a request
        StudentService request = new StudentService(studentRepository);

        // ... an existing Student is returned
        given(studentRepository.findStudentByEmail(student.getEmail()))
                .willReturn(Optional.of(student));

        // When
        assertThatThrownBy(() -> underTest.addNewStudent(student))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Email taken");

        // Then
        then(studentRepository).should(never()).save(any());
    }
}



























