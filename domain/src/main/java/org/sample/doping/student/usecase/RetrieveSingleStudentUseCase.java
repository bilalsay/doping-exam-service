package org.sample.doping.student.usecase;

import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;

@Data
@Builder
public class RetrieveSingleStudentUseCase implements UseCase {
    private Long studentId;
}
