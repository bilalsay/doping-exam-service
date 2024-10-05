package org.sample.doping.adapters;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.port.StudentPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentFakeDataAdapter implements StudentPort {

    @Override
    public Optional<Student> retrieveStudent(Long studentId) {
        return Optional.of(Student.builder()
                .id(studentId)
                .name("name")
                .surname("surname")
                .number("12345")
                .build());
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        student.setId(1L);
        return student;
    }

    @Override
    public Optional<Student> findStudentByNumber(String number) {
        return Optional.empty();
    }
}
