package org.sample.doping.student.handler;

import java.util.List;
import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.student.usecase.RetrieveStudentExamsUseCase;

@DomainComponent
public class RetrieveStudentExamsUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<List<StudentExam>, RetrieveStudentExamsUseCase> {

    private final ExamPort examPort;

    public RetrieveStudentExamsUseCaseHandler(ExamPort examPort) {
        this.examPort = examPort;
        register(RetrieveStudentExamsUseCase.class, this);
    }

    @Override
    public List<StudentExam> handle(RetrieveStudentExamsUseCase useCase) {
        return examPort.retrieveStudentExams(useCase.getStudentId());
    }
}
