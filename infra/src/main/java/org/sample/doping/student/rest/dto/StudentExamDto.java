package org.sample.doping.student.rest.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.rest.dto.ExamDto;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExamDto {

    private Long id;

    private StudentDto student;

    private ExamDto exam;

    @Builder.Default
    private Map<Long, String> answers = new HashMap<>();

    private Integer correctAnswerCount;

    private Integer totalQuestionCount;

    public static StudentExamDto fromModel(StudentExam studentExam) {
        return StudentExamDto.builder()
                .id(studentExam.getId())
                .student(StudentDto.fromModel(studentExam.getStudent()))
                .exam(ExamDto.fromModel(studentExam.getExam()))
                .answers(studentExam.getAnswers())
                .correctAnswerCount(studentExam.getCorrectAnswerCount())
                .totalQuestionCount(studentExam.getTotalQuestionCount())
                .build();
    }
}
