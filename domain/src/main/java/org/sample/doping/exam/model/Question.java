package org.sample.doping.exam.model;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Question {

    private Long id;

    private Integer number;

    private String text;

    private Exam exam;

    @Builder.Default
    private Map<String, String> selections = new HashMap<>();

    private String correctSelection;
}
