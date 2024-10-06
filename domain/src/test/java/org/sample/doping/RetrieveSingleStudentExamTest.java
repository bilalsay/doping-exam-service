package org.sample.doping;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sample.doping.adapters.ExamFakeDataAdapter;
import org.sample.doping.adapters.QuestionFakeDataAdapter;
import org.sample.doping.exam.handler.RetrieveExamQuestionsUseCaseHandler;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.student.handler.RetrieveSingleStudentExamUseCaseHandler;
import org.sample.doping.student.usecase.RetrieveSingleStudentExamUseCase;

class RetrieveSingleStudentExamTest {

    RetrieveSingleStudentExamUseCaseHandler retrieveSingleStudentExamUseCaseHandler;

    RetrieveExamQuestionsUseCaseHandler retrieveExamQuestionsUseCaseHandler;

    @BeforeEach
    void setUp() {
        retrieveSingleStudentExamUseCaseHandler = new RetrieveSingleStudentExamUseCaseHandler(new ExamFakeDataAdapter());
        retrieveExamQuestionsUseCaseHandler = new RetrieveExamQuestionsUseCaseHandler(new QuestionFakeDataAdapter());
    }

    @Test
    void should_return_student_exam() {
        // given
        var retrieveSingleStudentExamUseCase = RetrieveSingleStudentExamUseCase.builder()
                .studentId(1L)
                .examId(1L)
                .build();

        // when
        var studentExam = retrieveSingleStudentExamUseCaseHandler.handle(retrieveSingleStudentExamUseCase);

        // then
        assertThat(studentExam).isNotNull()
                .returns(1L, se -> se.getExam().getId())
                .returns(1L, se -> se.getStudent().getId())
                .returns("C", se -> se.getAnswers().get(2L))
                .returns(1, StudentExam::getCorrectAnswerCount);
    }

}
