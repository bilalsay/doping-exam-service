package org.sample.doping.student.jpa;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sample.doping.student.jpa.entity.StudentEntity;
import org.sample.doping.student.jpa.repository.StudentRepository;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.port.StudentPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentDataAdapter implements StudentPort {

    private final StudentRepository studentJpaRepository;

    @Override
    public Optional<Student> retrieveStudent(Long studentId) {
        return studentJpaRepository.findById(studentId)
                .map(StudentEntity::toModel);
    }

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        var studentEntity = new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setSurname(student.getSurname());
        studentEntity.setNumber(student.getNumber());
        return studentJpaRepository.save(studentEntity).toModel();
    }

    @Override
    public Optional<Student> findStudentByNumber(String number) {
        return studentJpaRepository.findStudentByNumber(number)
                .map(StudentEntity::toModel);
    }
}
