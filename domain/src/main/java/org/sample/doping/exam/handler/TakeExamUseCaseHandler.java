package org.sample.doping.exam.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.VoidUseCaseHandler;
import org.sample.doping.exam.exception.ExamNotFoundException;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.exam.usecase.TakeExamUseCase;
import org.sample.doping.student.exception.StudentNotFoundException;
import org.sample.doping.student.port.StudentPort;

@DomainComponent
public class TakeExamUseCaseHandler extends ObservableUseCasePublisher implements VoidUseCaseHandler<TakeExamUseCase> {

    private final ExamPort examPort;

    private final StudentPort studentPort;

    public TakeExamUseCaseHandler(ExamPort examPort, StudentPort studentPort) {
        this.examPort = examPort;
        this.studentPort = studentPort;
        register(TakeExamUseCase.class, this);
    }

    @Override
    public void handle(TakeExamUseCase useCase) {
        var exam = examPort.retrieveExam(useCase.getExamId())
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + useCase.getExamId()));
        var student = studentPort.retrieveStudent(useCase.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + useCase.getStudentId()));
        examPort.assignExamToStudent(exam, student);
    }
}
