package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.ExamFakeDataAdapter;
import org.sample.doping.adapters.QuestionFakeDataAdapter;
import org.sample.doping.exam.handler.RetrieveExamQuestionsUseCaseHandler;
import org.sample.doping.student.handler.RetrieveStudentExamsUseCaseHandler;
import org.sample.doping.student.usecase.RetrieveStudentExamsUseCase;

class RetrieveStudentExamsTest {

    RetrieveStudentExamsUseCaseHandler retrieveStudentExamsUseCaseHandler;

    RetrieveExamQuestionsUseCaseHandler retrieveExamQuestionsUseCaseHandler;

    @BeforeEach
    void setUp() {
        retrieveStudentExamsUseCaseHandler = new RetrieveStudentExamsUseCaseHandler(new ExamFakeDataAdapter());
        retrieveExamQuestionsUseCaseHandler = new RetrieveExamQuestionsUseCaseHandler(new QuestionFakeDataAdapter());
    }

    @Test
    void should_return_student_exams() {
        // given
        var retrieveStudentExamsUseCase = RetrieveStudentExamsUseCase.builder()
                .studentId(1L)
                .build();

        // when
        var studentExams = retrieveStudentExamsUseCaseHandler.handle(retrieveStudentExamsUseCase);

        // then
        assertThat(studentExams).isNotNull()
                .returns("C", se -> se.get(0).getAnswers().get(2L));
    }

}
