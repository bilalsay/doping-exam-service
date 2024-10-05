package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.StudentFakeDataAdapter;
import org.sample.doping.student.handler.RetrieveSingleStudentUseCaseHandler;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.usecase.RetrieveSingleStudentUseCase;

class RetrieveStudentTest {

    RetrieveSingleStudentUseCaseHandler retrieveSingleStudentUseCaseHandler;

    @BeforeEach
    void setUp() {
        retrieveSingleStudentUseCaseHandler = new RetrieveSingleStudentUseCaseHandler(new StudentFakeDataAdapter());
    }

    @Test
    void should_return_student() {
        // given
        var retrieveSingleStudentUseCase = RetrieveSingleStudentUseCase.builder()
                .studentId(1L)
                .build();

        // when
        var student = retrieveSingleStudentUseCaseHandler.handle(retrieveSingleStudentUseCase);

        // then
        assertThat(student).isNotNull()
                .returns(1L, Student::getId)
                .returns("name", Student::getName)
                .returns("surname", Student::getSurname)
                .returns("12345", Student::getNumber);
    }

}
