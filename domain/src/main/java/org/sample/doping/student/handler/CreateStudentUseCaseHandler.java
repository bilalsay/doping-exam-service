package org.sample.doping.student.handler;

import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.student.exception.StudentAllReadyExistsException;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.port.StudentPort;
import org.sample.doping.student.usecase.CreateStudentUseCase;

@DomainComponent
public class CreateStudentUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<Student, CreateStudentUseCase> {

    private final StudentPort studentPort;

    public CreateStudentUseCaseHandler(StudentPort studentPort) {
        this.studentPort = studentPort;
        register(CreateStudentUseCase.class, this);
    }

    @Override
    public Student handle(CreateStudentUseCase useCase) {
        var student = Student.builder()
                .name(useCase.getName())
                .surname(useCase.getSurname())
                .number(useCase.getNumber())
                .build();

        var existStudent = studentPort.findStudentByNumber(useCase.getNumber());

        if (existStudent.isPresent()) {
            throw new StudentAllReadyExistsException("Student already exists with number: " + useCase.getNumber());
        }

        return studentPort.saveStudent(student);
    }
}
