package org.sample.doping.exam.jpa.repository;

import java.util.Optional;
import org.sample.doping.exam.jpa.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {

    Optional<ExamEntity> findById(Long id);
}
