package org.sample.doping.exam.usecase;

import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;


@Data
@Builder
public class TakeExamUseCase implements UseCase {

    private Long studentId;

    private Long examId;
}
