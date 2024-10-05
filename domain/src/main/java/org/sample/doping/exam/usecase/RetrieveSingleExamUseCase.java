package org.sample.doping.exam.usecase;

import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;

@Data
@Builder
public class RetrieveSingleExamUseCase implements UseCase {
    private Long examId;
}
