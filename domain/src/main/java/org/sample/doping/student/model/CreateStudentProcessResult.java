package org.sample.doping.student.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateStudentProcessResult {

    private String status;
    private String message;
}
