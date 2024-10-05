package org.sample.doping.exam.handler;

import java.util.List;
import org.sample.doping.common.DomainComponent;
import org.sample.doping.common.usecase.ObservableUseCasePublisher;
import org.sample.doping.common.usecase.UseCaseHandler;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.port.QuestionPort;
import org.sample.doping.exam.usecase.RetrieveExamQuestionsUseCase;

@DomainComponent
public class RetrieveExamQuestionsUseCaseHandler extends ObservableUseCasePublisher implements UseCaseHandler<List<Question>, RetrieveExamQuestionsUseCase> {

    private final QuestionPort questionPort;

    public RetrieveExamQuestionsUseCaseHandler(QuestionPort questionPort) {
        this.questionPort = questionPort;
        register(RetrieveExamQuestionsUseCase.class, this);
    }

    @Override
    public List<Question> handle(RetrieveExamQuestionsUseCase useCase) {
        return questionPort.retrieveExamQuestions(useCase.getExamId());
    }
}
