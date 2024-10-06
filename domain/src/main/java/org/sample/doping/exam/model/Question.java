package org.sample.doping.exam.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    private Long id;

    private Integer number;

    private String text;

    private Exam exam;

    @Builder.Default
    private Map<String, String> selections = new HashMap<>();

    private String correctSelection;
}
