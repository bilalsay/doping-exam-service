package org.sample.doping.exam.jpa.repository;

import org.sample.doping.exam.jpa.entity.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentExamRepository extends JpaRepository<StudentExamEntity, Long> {

}
