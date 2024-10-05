package org.sample.doping.student.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Student {
    private Long id;
    private String name;
    private String surname;
    private String number;
}
