package org.sample.doping.student.port;

import java.util.Optional;
import org.sample.doping.student.model.Student;

public interface StudentPort {

    Optional<Student> retrieveStudent(Long studentId);

    Student saveStudent(Student student);

    Optional<Student> findStudentByNumber(String number);
}
