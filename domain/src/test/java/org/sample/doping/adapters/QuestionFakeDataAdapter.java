package org.sample.doping.adapters;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.port.QuestionPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionFakeDataAdapter implements QuestionPort {


    public List<Question> retrieveExamQuestions(Long examId) {
        return List.of(Question.builder()
                        .id(1L)
                        .correctSelection("A")
                        .build(),
                Question.builder()
                        .id(2L)
                        .correctSelection("B")
                        .build());
    }

    @Override
    @Transactional
    public Question saveQuestion(Question question, Long examId) {
        return question;
    }


}
