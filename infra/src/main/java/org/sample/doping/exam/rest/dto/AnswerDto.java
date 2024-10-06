package org.sample.doping.exam.rest.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AnswerDto {

    private Long questionId;

    private String selection;
}
