package org.sample.doping.exam.rest.dto;

import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.usecase.CreateExamUseCase;


@Data
@Builder
public class CreateExamRequest {

    @NotNull
    private String name;

    @Builder.Default
    private List<Question> questions = new ArrayList<>();

    public CreateExamUseCase toUseCase() {
        return CreateExamUseCase.builder()
                .name(getName())
                .questions(getQuestions())
                .build();
    }
}
