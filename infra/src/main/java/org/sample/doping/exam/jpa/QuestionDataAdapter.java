package org.sample.doping.exam.jpa;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sample.doping.exam.jpa.entity.QuestionEntity;
import org.sample.doping.exam.jpa.repository.QuestionRepository;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.port.QuestionPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionDataAdapter implements QuestionPort {

    private final QuestionRepository questionRepository;

    @Override
    public List<Question> retrieveExamQuestions(Long examId) {
        return questionRepository.findAllByExamId(examId).stream()
                .map(QuestionEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Question saveQuestion(Question question, Long examId) {
        return null;
    }


}
