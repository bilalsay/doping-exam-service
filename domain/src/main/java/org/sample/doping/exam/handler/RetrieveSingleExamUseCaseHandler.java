package org.sample.doping.exam.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.exam.exception.ExamNotFoundException;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.exam.usecase.RetrieveSingleExamUseCase;

@DomainComponent
public class RetrieveSingleExamUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<Exam, RetrieveSingleExamUseCase> {

    private final ExamPort examPort;

    public RetrieveSingleExamUseCaseHandler(ExamPort examPort) {
        this.examPort = examPort;
        register(RetrieveSingleExamUseCase.class, this);
    }

    @Override
    public Exam handle(RetrieveSingleExamUseCase useCase) {
        return examPort.retrieveExam(useCase.getExamId())
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + useCase.getExamId()));
    }
}
