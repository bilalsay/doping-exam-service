package org.sample.doping.exam.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sample.doping.common.BaseController;
import org.sample.doping.common.DataResponse;
import org.sample.doping.common.Response;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.rest.dto.CreateExamRequest;
import org.sample.doping.exam.rest.dto.ExamDto;
import org.sample.doping.exam.rest.dto.QuestionDto;
import org.sample.doping.exam.rest.dto.TakeExamRequest;
import org.sample.doping.exam.usecase.RetrieveExamQuestionsUseCase;
import org.sample.doping.exam.usecase.RetrieveSingleExamUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/exams")
@RequiredArgsConstructor
public class ExamController extends BaseController {

    @GetMapping(value = "/{examId}")
    public Response<ExamDto> getExam(@PathVariable Long examId) {
        var retrieveSingleExam = RetrieveSingleExamUseCase.builder()
                .examId(examId)
                .build();
        var exam = publish(Exam.class, retrieveSingleExam);
        var questions = publish(List.class, RetrieveExamQuestionsUseCase.builder().examId(examId).build());
        return respond(ExamDto.fromModel(exam, questions));
    }

    @PostMapping
    public Response<ExamDto> createExam(@RequestBody @Valid CreateExamRequest createExamRequest) {
        var createExamUseCase = createExamRequest.toUseCase();
        var exam = publish(Exam.class, createExamUseCase);
        return respond(ExamDto.fromModel(exam, createExamRequest.getQuestions()));
    }

    @GetMapping(value = "/{examId}/questions")
    public Response<DataResponse<QuestionDto>> getExamQuestions(@PathVariable Long examId) {
        var retrieveExamQuestionsUseCase = RetrieveExamQuestionsUseCase.builder()
                .examId(examId)
                .build();
        var questions = ((List<Question>) publish(List.class, RetrieveExamQuestionsUseCase.builder().examId(examId).build())).stream()
                .map(QuestionDto::fromModel)
                .collect(Collectors.toList());

        return respond(questions);
    }

    @PostMapping("/take")
    public void takeExam(@RequestBody @Valid TakeExamRequest takeExamRequest) {
        var createExamUseCase = takeExamRequest.toUseCase();
        publish(createExamUseCase);
    }

}
