package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.QuestionFakeDataAdapter;
import org.sample.doping.exam.handler.RetrieveExamQuestionsUseCaseHandler;
import org.sample.doping.exam.usecase.RetrieveExamQuestionsUseCase;

class RetrieveExamsQuestionsTest {

    RetrieveExamQuestionsUseCaseHandler retrieveExamQuestionsUseCaseHandler;

    @BeforeEach
    void setUp() {
        retrieveExamQuestionsUseCaseHandler = new RetrieveExamQuestionsUseCaseHandler(new QuestionFakeDataAdapter());
    }

    @Test
    void should_return_exam_questions() {
        // given
        var retrieveExamQuestionsUseCase = RetrieveExamQuestionsUseCase.builder()
                .examId(1L)
                .build();

        // when
        var examQuestions = retrieveExamQuestionsUseCaseHandler.handle(retrieveExamQuestionsUseCase);

        // then
        assertThat(examQuestions).isNotNull()
                .returns("A", eq -> eq.get(0).getCorrectSelection());
    }

}
