package org.sample.doping.student.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.student.usecase.CreateStudentUseCase;


@Data
@Builder
public class CreateStudentRequest {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String number;

    public CreateStudentUseCase toUseCase() {
        return CreateStudentUseCase.builder()
                .name(getName())
                .surname(getSurname())
                .number(getNumber())
                .build();
    }
}
