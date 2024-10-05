package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.StudentFakeDataAdapter;
import org.sample.doping.student.handler.CreateStudentUseCaseHandler;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.usecase.CreateStudentUseCase;

class CreateStudentTest {

    CreateStudentUseCaseHandler createStudentUseCaseHandler;

    @BeforeEach
    void setUp() {
        createStudentUseCaseHandler = new CreateStudentUseCaseHandler(new StudentFakeDataAdapter());
    }

    @Test
    void should_create_student() {
        // given
        var createStudentUseCase = CreateStudentUseCase.builder()
                .name("name")
                .surname("surname")
                .number("12345")
                .build();

        // when
        var student = createStudentUseCaseHandler.handle(createStudentUseCase);

        // then
        assertThat(student).isNotNull()
                .returns(1L, Student::getId)
                .returns("name", Student::getName)
                .returns("surname", Student::getSurname)
                .returns("12345", Student::getNumber);
    }

}
