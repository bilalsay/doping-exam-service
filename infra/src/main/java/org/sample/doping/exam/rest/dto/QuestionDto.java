package org.sample.doping.exam.rest.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sample.doping.exam.model.Question;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long id;

    private Integer number;

    private String text;

    @Builder.Default
    private Map<String, String> selections = new HashMap<>();

    private String correctSelection;

    public static QuestionDto fromModel(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .number(question.getNumber())
                .text(question.getText())
                .selections(question.getSelections())
                .correctSelection(question.getCorrectSelection())
                .build();
    }
}
