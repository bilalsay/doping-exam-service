package org.sample.doping.exam.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.sample.doping.exam.jpa.entity.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {

    List<StudentExamEntity> findAllByStudentId(Long studentId);

    Optional<StudentExamEntity> findByStudentIdAndExamId(Long studentId, Long examId);
}
