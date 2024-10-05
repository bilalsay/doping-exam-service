package org.sample.doping.exam.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.student.model.Student;


@Data
@Builder
public class StudentExam {

    private Student student;

    private Exam exam;

    @Builder.Default
    private List<Answer> answers = new ArrayList<>();
}
