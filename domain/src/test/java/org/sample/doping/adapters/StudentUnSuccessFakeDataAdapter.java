package org.sample.doping.adapters;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.port.StudentPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentUnSuccessFakeDataAdapter implements StudentPort {

    @Override
    public Optional<Student> retrieveStudent(Long studentId) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        student.setId(1L);
        return student;
    }

    @Override
    public Optional<Student> findStudentByNumber(String number) {
        return Optional.of(Student.builder().number(number).build());
    }
}
