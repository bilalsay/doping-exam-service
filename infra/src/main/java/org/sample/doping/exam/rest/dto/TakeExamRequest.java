package org.sample.doping.exam.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.exam.usecase.TakeExamUseCase;


@Data
@Builder
public class TakeExamRequest {

    @NotNull
    private Long studentId;

    @NotNull
    private Long examId;

    public TakeExamUseCase toUseCase() {
        return TakeExamUseCase.builder()
                .studentId(getStudentId())
                .examId(getExamId())
                .build();
    }
}
