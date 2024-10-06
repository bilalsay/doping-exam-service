package org.sample.doping.exam.rest.dto;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.exam.usecase.CompleteExamUseCase;


@Data
@Builder
public class CompleteExamRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long examId;

    private Map<Long, String> answers;

    public CompleteExamUseCase toUseCase() {
        return CompleteExamUseCase.builder()
                .studentId(getStudentId())
                .examId(getExamId())
                .answers(getAnswers())
                .build();
    }
}
