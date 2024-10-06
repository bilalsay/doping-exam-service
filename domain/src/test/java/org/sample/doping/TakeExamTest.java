package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.ExamFakeDataAdapter;
import org.sample.doping.adapters.ExamUnSuccessFakeDataAdapter;
import org.sample.doping.adapters.StudentFakeDataAdapter;
import org.sample.doping.adapters.StudentUnSuccessFakeDataAdapter;
import org.sample.doping.exam.exception.ExamNotFoundException;
import org.sample.doping.exam.handler.TakeExamUseCaseHandler;
import org.sample.doping.exam.usecase.TakeExamUseCase;
import org.sample.doping.student.exception.StudentNotFoundException;

class TakeExamTest {

    TakeExamUseCaseHandler takeExamUseCaseHandler;

    @BeforeEach
    void setUp() {
        takeExamUseCaseHandler = new TakeExamUseCaseHandler(new ExamFakeDataAdapter(), new StudentFakeDataAdapter());
    }

    @Test
    void should_take_exam() {
        // given
        var takeExamUseCase = TakeExamUseCase.builder()
                .studentId(1L)
                .examId(1L)
                .build();

        // when
        assertThatNoException().isThrownBy(() -> takeExamUseCaseHandler.handle(takeExamUseCase));
    }

    @Test
    void should_not_take_exam_when_not_found_student() {
        takeExamUseCaseHandler = new TakeExamUseCaseHandler(new ExamFakeDataAdapter(), new StudentUnSuccessFakeDataAdapter());

        // given
        var takeExamUseCase = TakeExamUseCase.builder()
                .studentId(1L)
                .examId(1L)
                .build();

        // when
        assertThatThrownBy(() -> takeExamUseCaseHandler.handle(takeExamUseCase))
                .isInstanceOf(StudentNotFoundException.class);

    }

    @Test
    void should_not_take_exam_when_not_found_exam() {
        takeExamUseCaseHandler = new TakeExamUseCaseHandler(new ExamUnSuccessFakeDataAdapter(), new StudentFakeDataAdapter());

        // given
        var takeExamUseCase = TakeExamUseCase.builder()
                .studentId(1L)
                .examId(1L)
                .build();

        // when
        assertThatThrownBy(() -> takeExamUseCaseHandler.handle(takeExamUseCase))
                .isInstanceOf(ExamNotFoundException.class);

    }

}
