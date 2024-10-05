package org.sample.doping.exam.model;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Answer {

    private Long questionId;

    private String selection;
}
