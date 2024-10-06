package org.sample.doping;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.sample.doping.exam.jpa.ExamDataAdapter;
import org.sample.doping.exam.jpa.entity.ExamEntity;
import org.sample.doping.exam.jpa.entity.QuestionEntity;
import org.sample.doping.exam.jpa.entity.StudentExamEntity;
import org.sample.doping.exam.jpa.repository.ExamRepository;
import org.sample.doping.exam.jpa.repository.QuestionRepository;
import org.sample.doping.exam.jpa.repository.StudentExamRepository;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.student.jpa.entity.StudentEntity;
import org.sample.doping.student.model.Student;

@ExtendWith(MockitoExtension.class)
class ExamDataAdapterTest {

    @InjectMocks
    private ExamDataAdapter examDataAdapter;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private StudentExamRepository studentExamRepository;

    @Test
    void should_retrieve_exam() {
        // given
        var examId = 1L;

        var examEntity = ExamEntity.builder()
                .name("exam")
                .build();
        examEntity.setId(examId);

        when(examRepository.findById(examId))
                .thenReturn(Optional.of(examEntity));

        // when
        var exam = examDataAdapter.retrieveExam(examId);
        assertEquals(exam.get().getName(), examEntity.getName());

        // then
        verify(examRepository, times(1)).findById(examId);
    }

    @Test
    void should_create_exam() {
        // given
        var questions = List.of(Question.builder()
                .selections(Map.of("A", "Ali", "B", "Veli"))
                .text("soru1?")
                .number(1)
                .correctSelection("A")
                .build());


        var exam = Exam.builder()
                .name("exam")
                .questions(questions)
                .build();

        var examEntity = ExamEntity.builder()
                .name("exam")
                .build();

        var questionsEntities = questions.stream()
                .map(question -> QuestionEntity.builder()
                        .exam(examEntity)
                        .selections(Map.of("A", "Ali", "B", "Veli"))
                        .text("soru1?")
                        .number(1)
                        .correctSelection("A")
                        .build())
                .toList();

        when(examRepository.save(examEntity))
                .thenReturn(examEntity);
        when(questionRepository.saveAll(questionsEntities))
                .thenReturn(questionsEntities);

        // when
        var savedExam = examDataAdapter.saveExam(exam);
        assertEquals(savedExam.getName(), examEntity.getName());

        // then
        verify(examRepository, times(1)).save(examEntity);
        verify(questionRepository, times(1)).saveAll(questionsEntities);
    }

    @Test
    void should_assign_exam_to_student() {
        // given
        var exam = Exam.builder()
                .id(1L)
                .name("exam")
                .build();

        var examEntity = ExamEntity.builder()
                .build();

        var student = Student.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .number("12345")
                .build();
        examEntity.setId(1L);

        var studentEntity = StudentEntity.builder()
                .build();
        studentEntity.setId(1L);

        var studentExam = StudentExamEntity.builder()
                .exam(examEntity)
                .student(studentEntity)
                .build();

        when(studentExamRepository.save(studentExam))
                .thenReturn(studentExam);

        // when
        assertDoesNotThrow(() -> examDataAdapter.assignExamToStudent(exam, student));

        // then
        verify(studentExamRepository, times(1)).save(studentExam);
    }

    @Test
    void should_return_student_exams() {
        // given
        var exam = Exam.builder()
                .id(1L)
                .name("exam")
                .build();

        var examEntity = ExamEntity.builder()
                .name("exam")
                .build();

        examEntity.setId(1L);

        var studentEntity = StudentEntity.builder()
                .name("name")
                .surname("surname")
                .number("12345")
                .build();
        studentEntity.setId(1L);

        var studentExamEntity = StudentExamEntity.builder()
                .exam(examEntity)
                .student(studentEntity)
                .build();

        var studentExamEntities = List.of(studentExamEntity);

        when(studentExamRepository.findAllByStudentId(1L))
                .thenReturn(studentExamEntities);

        // when
        var studentExams = examDataAdapter.retrieveStudentExams(1L);

        assertEquals("12345", studentExams.get(0).getStudent().getNumber());



        // then
        verify(studentExamRepository, times(1)).findAllByStudentId(1L);
    }

    @Test
    void should_return_student_exam() {
        // given
        var examEntity = ExamEntity.builder()
                .name("exam")
                .build();

        examEntity.setId(1L);

        var studentEntity = StudentEntity.builder()
                .name("name")
                .surname("surname")
                .number("12345")
                .build();
        studentEntity.setId(1L);

        var studentExamEntity = StudentExamEntity.builder()
                .exam(examEntity)
                .student(studentEntity)
                .build();

        when(studentExamRepository.findByStudentIdAndExamId(1L, 1L))
                .thenReturn(Optional.of(studentExamEntity));

        // when
        var studentExam = examDataAdapter.retrieveStudentExam(1L, 1L);

        assertEquals("12345", studentExam.get().getStudent().getNumber());

        // then
        verify(studentExamRepository, times(1)).findByStudentIdAndExamId(1L, 1L);
    }

    @Test
    void should_complete_exam() {
        // given
        var exam = Exam.builder()
                .id(1L)
                .name("exam")
                .build();

        var examEntity = ExamEntity.builder()
                .build();

        var student = Student.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .number("12345")
                .build();
        examEntity.setId(1L);

        var studentEntity = StudentEntity.builder()
                .build();
        studentEntity.setId(1L);

        var studentExamEntity = StudentExamEntity.builder()
                .exam(examEntity)
                .student(studentEntity)
                .answers(Map.of(1L, "A", 2L, "B"))
                .build();

        var studentExam = StudentExam.builder()
                .exam(exam)
                .student(student)
                .answers(Map.of(1L, "A", 2L, "B"))
                .build();

        when(studentExamRepository.save(studentExamEntity))
                .thenReturn(studentExamEntity);

        // when
        assertDoesNotThrow(() -> examDataAdapter.completeStudentExam(studentExam));

        // then
        verify(studentExamRepository, times(1)).save(studentExamEntity);
    }

}
