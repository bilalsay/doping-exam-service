package org.sample.doping.exam.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Exam {

    private Long id;

    private String name;

    @Builder.Default
    private List<Question> questions = new ArrayList<>();
}
