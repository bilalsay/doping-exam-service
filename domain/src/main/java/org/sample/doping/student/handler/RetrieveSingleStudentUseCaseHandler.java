package org.sample.doping.student.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.student.exception.StudentNotFoundException;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.port.StudentPort;
import org.sample.doping.student.usecase.RetrieveSingleStudentUseCase;

@DomainComponent
public class RetrieveSingleStudentUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<Student, RetrieveSingleStudentUseCase> {

    private final StudentPort studentPort;

    public RetrieveSingleStudentUseCaseHandler(StudentPort studentPort) {
        this.studentPort = studentPort;
        register(RetrieveSingleStudentUseCase.class, this);
    }

    @Override
    public Student handle(RetrieveSingleStudentUseCase useCase) {
        return studentPort.retrieveStudent(useCase.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + useCase.getStudentId()));
    }
}
