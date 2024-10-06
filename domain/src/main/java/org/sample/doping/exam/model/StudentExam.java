package org.sample.doping.exam.model;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sample.doping.student.model.Student;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentExam {

    private Long id;

    private Student student;

    private Exam exam;

    @Builder.Default
    private Map<Long, String> answers = new HashMap<>();

    private Integer correctAnswerCount;

    private Integer totalQuestionCount;
}
