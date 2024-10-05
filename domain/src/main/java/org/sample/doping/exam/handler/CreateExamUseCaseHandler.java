package org.sample.doping.exam.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.exam.usecase.CreateExamUseCase;

@DomainComponent
public class CreateExamUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<Exam, CreateExamUseCase> {

    private final ExamPort examPort;

    public CreateExamUseCaseHandler(ExamPort examPort) {
        this.examPort = examPort;
        register(CreateExamUseCase.class, this);
    }

    @Override
    public Exam handle(CreateExamUseCase useCase) {
        var exam = Exam.builder()
                .name(useCase.getName())
                .questions(useCase.getQuestions())
                .build();

        return examPort.saveExam(exam);
    }
}
