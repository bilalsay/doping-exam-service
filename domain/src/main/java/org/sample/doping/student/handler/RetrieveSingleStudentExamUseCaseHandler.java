package org.sample.doping.student.handler;

import java.util.List;
import java.util.stream.Collectors;
import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.exam.usecase.RetrieveExamQuestionsUseCase;
import org.sample.doping.student.exception.StudentExamNotFoundException;
import org.sample.doping.student.usecase.RetrieveSingleStudentExamUseCase;

@DomainComponent
public class RetrieveSingleStudentExamUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<StudentExam, RetrieveSingleStudentExamUseCase> {

    private final ExamPort examPort;

    public RetrieveSingleStudentExamUseCaseHandler(ExamPort examPort) {
        this.examPort = examPort;
        register(RetrieveSingleStudentExamUseCase.class, this);
    }

    @Override
    public StudentExam handle(RetrieveSingleStudentExamUseCase useCase) {
        return examPort.retrieveStudentExam(useCase.getStudentId(), useCase.getExamId())
                .map(studentExam -> {
                    var retrieveExamQuestionsUseCase = RetrieveExamQuestionsUseCase.builder()
                            .examId(useCase.getExamId())
                            .build();
                    var questions = ((List<Question>) publish(List.class, retrieveExamQuestionsUseCase)).stream()
                            .collect(Collectors.toList());
                    var answers = studentExam.getAnswers();
                    var correctAnswerCount = questions.stream()
                            .filter(question -> question.getCorrectSelection().equals(answers.getOrDefault(question.getId(), "NONE")))
                            .count();
                    studentExam.setCorrectAnswerCount(Long.valueOf(correctAnswerCount).intValue());
                    studentExam.setTotalQuestionCount(questions.size());
                    studentExam.getExam().setQuestions(questions);
                    return studentExam;
                })
                .orElseThrow(() -> new StudentExamNotFoundException("Exam not found with id: " + useCase.getExamId()));
    }
}
