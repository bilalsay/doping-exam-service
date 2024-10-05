package org.sample.doping.exam.rest.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamDto {

    private Long id;

    private String name;

    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public static ExamDto fromModel(Exam exam, List<Question> questions) {
        return ExamDto.builder()
                .id(exam.getId())
                .name(exam.getName())
                .questions(questions)
                .build();
    }
}
