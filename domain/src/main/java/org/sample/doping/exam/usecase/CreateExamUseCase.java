package org.sample.doping.exam.usecase;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.sample.doping.common.model.UseCase;
import org.sample.doping.exam.model.Question;


@Data
@Builder
public class CreateExamUseCase implements UseCase {
    private String name;

    @Builder.Default
    private List<Question> questions = new ArrayList<>();
}
