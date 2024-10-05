package org.sample.doping.exam.port;

import java.util.List;
import org.sample.doping.exam.model.Question;

public interface QuestionPort {

    List<Question> retrieveExamQuestions(Long examId);

    Question saveQuestion(Question question, Long examId);

}
