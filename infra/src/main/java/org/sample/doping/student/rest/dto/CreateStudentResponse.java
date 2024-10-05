package org.sample.doping.student.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.sample.doping.student.model.CreateStudentProcessResult;

@Data
@Builder
public class CreateStudentResponse {

    private String status;
    private String message;

    public static CreateStudentResponse fromModel(CreateStudentProcessResult createStudentProcessResult) {
        return CreateStudentResponse.builder()
                .status(createStudentProcessResult.getStatus())
                .message(createStudentProcessResult.getMessage())
                .build();
    }
}
