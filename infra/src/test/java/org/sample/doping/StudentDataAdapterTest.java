package org.sample.doping;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sample.doping.student.jpa.StudentDataAdapter;
import org.sample.doping.student.jpa.entity.StudentEntity;
import org.sample.doping.student.jpa.repository.StudentRepository;
import org.sample.doping.student.model.Student;

@ExtendWith(MockitoExtension.class)
class StudentDataAdapterTest {

    @InjectMocks
    private StudentDataAdapter studentDataAdapter;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void should_save_student() {
        // given
        var student = Student.builder()
                .name("name")
                .surname("surname")
                .number("12345")
                .build();

        var studentEntity = StudentEntity.builder()
                .name("name")
                .surname("surname")
                .number("12345")
                .build();

        when(studentRepository.save(studentEntity)).thenReturn(studentEntity);

        // when
        var savedStudent = studentDataAdapter.saveStudent(student);
        assertEquals(savedStudent.getNumber(), student.getNumber());

        // then
        verify(studentRepository, times(1)).save(studentEntity);
    }

}
