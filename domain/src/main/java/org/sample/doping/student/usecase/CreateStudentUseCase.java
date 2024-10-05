package org.sample.doping.student.usecase;

import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;


@Data
@Builder
public class CreateStudentUseCase implements UseCase {
    private String name;
    private String surname;
    private String number;
}
