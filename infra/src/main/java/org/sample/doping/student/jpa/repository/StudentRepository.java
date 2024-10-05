package org.sample.doping.student.jpa.repository;

import java.util.Optional;
import org.sample.doping.student.jpa.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findById(Long id);

    Optional<StudentEntity> findStudentByNumber(String number);
}
