package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.ExamFakeDataAdapter;
import org.sample.doping.exam.handler.CreateExamUseCaseHandler;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.usecase.CreateExamUseCase;

class CreateExamTest {

    CreateExamUseCaseHandler createExamUseCaseHandler;

    @BeforeEach
    void setUp() {
        createExamUseCaseHandler = new CreateExamUseCaseHandler(new ExamFakeDataAdapter());
    }

    @Test
    void should_create_exam() {
        // given
        var questions = List.of(Question.builder()
                        .number(1)
                        .text("Soru1?")
                        .selections(Map.of("A", "Ali", "B", "Veli"))
                .build(),
                Question.builder()
                        .number(2)
                        .text("Soru2?")
                        .selections(Map.of("A", "Hasan", "B", "Hüseyin"))
                        .build());
        var createExamUseCase = CreateExamUseCase.builder()
                .name("name")
                .questions(questions)
                .build();

        // when
        var exam = createExamUseCaseHandler.handle(createExamUseCase);

        // then
        assertThat(exam).isNotNull()
                .returns(33L, Exam::getId)
                .returns("Ali", e -> e.getQuestions().get(0).getSelections().get("A"));
    }

}
