package org.sample.doping.exam.jpa.repository;

import java.util.List;
import java.util.Optional;
import org.sample.doping.exam.jpa.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findById(Long id);

    List<QuestionEntity> findAllByExamId(Long examId);
}
