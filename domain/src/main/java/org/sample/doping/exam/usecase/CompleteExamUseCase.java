package org.sample.doping.exam.usecase;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;


@Data
@Builder
public class CompleteExamUseCase implements UseCase {

    private Long studentId;

    private Long examId;

    private Map<Long, String> answers;
}
