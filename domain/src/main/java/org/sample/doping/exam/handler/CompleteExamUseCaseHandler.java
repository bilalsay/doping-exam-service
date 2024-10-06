package org.sample.doping.exam.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.VoidUseCaseHandler;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.exam.usecase.CompleteExamUseCase;
import org.sample.doping.student.usecase.RetrieveSingleStudentExamUseCase;

@DomainComponent
public class CompleteExamUseCaseHandler extends ObservableUseCasePublisher implements VoidUseCaseHandler<CompleteExamUseCase> {

    private final ExamPort examPort;

    public CompleteExamUseCaseHandler(ExamPort examPort) {
        this.examPort = examPort;
        register(CompleteExamUseCase.class, this);
    }

    @Override
    public void handle(CompleteExamUseCase useCase) {
        var retrieveSingleStudentExamUseCase = RetrieveSingleStudentExamUseCase.builder()
                .studentId(useCase.getStudentId())
                .examId(useCase.getExamId())
                .build();
        var studentExam = publish(StudentExam.class, retrieveSingleStudentExamUseCase);
        studentExam.setAnswers(useCase.getAnswers());
        examPort.completeStudentExam(studentExam);
    }
}
