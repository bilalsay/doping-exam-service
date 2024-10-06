package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.ExamFakeDataAdapter;
import org.sample.doping.adapters.QuestionFakeDataAdapter;
import org.sample.doping.exam.handler.CompleteExamUseCaseHandler;
import org.sample.doping.exam.handler.RetrieveExamQuestionsUseCaseHandler;
import org.sample.doping.exam.usecase.CompleteExamUseCase;
import org.sample.doping.student.handler.RetrieveSingleStudentExamUseCaseHandler;

class CompleteExamTest {

    CompleteExamUseCaseHandler completeExamUseCaseHandler;

    RetrieveSingleStudentExamUseCaseHandler retrieveSingleStudentExamUseCaseHandler;

    RetrieveExamQuestionsUseCaseHandler retrieveExamQuestionsUseCaseHandler;

    @BeforeEach
    void setUp() {
        completeExamUseCaseHandler = new CompleteExamUseCaseHandler(new ExamFakeDataAdapter());
        retrieveSingleStudentExamUseCaseHandler = new RetrieveSingleStudentExamUseCaseHandler(new ExamFakeDataAdapter());
        retrieveExamQuestionsUseCaseHandler = new RetrieveExamQuestionsUseCaseHandler(new QuestionFakeDataAdapter());
    }

    @Test
    void should_complete_exam() {
        // given
        var completeExamUseCase = CompleteExamUseCase.builder()
                .studentId(1L)
                .examId(1L)
                .answers(Map.of(1L, "A", 2L, "C"))
                .build();

        // when
        assertThatNoException().isThrownBy(() -> completeExamUseCaseHandler.handle(completeExamUseCase));
    }

}
