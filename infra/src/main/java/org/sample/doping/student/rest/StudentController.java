package org.sample.doping.student.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sample.doping.common.BaseController;
import org.sample.doping.common.DataResponse;
import org.sample.doping.common.Response;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.student.model.Student;
import org.sample.doping.student.rest.dto.CreateStudentRequest;
import org.sample.doping.student.rest.dto.StudentDto;
import org.sample.doping.student.rest.dto.StudentExamDto;
import org.sample.doping.student.usecase.RetrieveSingleStudentExamUseCase;
import org.sample.doping.student.usecase.RetrieveSingleStudentUseCase;
import org.sample.doping.student.usecase.RetrieveStudentExamsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/students")
@RequiredArgsConstructor
public class StudentController extends BaseController {

    @GetMapping(value = "/{studentId}")
    public Response<StudentDto> getStudent(@PathVariable Long studentId) {
        var retrieveSingleStudent = RetrieveSingleStudentUseCase.builder()
                .studentId(studentId)
                .build();
        var student = publish(Student.class, retrieveSingleStudent);
        return respond(StudentDto.fromModel(student));
    }

    @PostMapping
    public Response<StudentDto> createStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest) {
        var createStudentUseCase = createStudentRequest.toUseCase();
        var student = publish(Student.class, createStudentUseCase);
        return respond(StudentDto.fromModel(student));
    }

    @GetMapping(value = "/{studentId}/exams")
    public Response<DataResponse<StudentExamDto>> getExams(@PathVariable Long studentId) {
        var retrieveStudentExamsUseCase = RetrieveStudentExamsUseCase.builder()
                .studentId(studentId)
                .build();
        var studentExams = ((List<StudentExam>) publish(List.class, retrieveStudentExamsUseCase)).stream()
                .map(StudentExamDto::fromModel)
                .collect(Collectors.toList());
        return respond(studentExams);
    }

    @GetMapping(value = "/{studentId}/exam/{examId}")
    public Response<StudentExamDto> getExam(@PathVariable Long studentId, @PathVariable Long examId) {
        var retrieveSingleStudentExamUseCase = RetrieveSingleStudentExamUseCase.builder()
                .studentId(studentId)
                .examId(examId)
                .build();
        var studentExam = publish(StudentExam.class, retrieveSingleStudentExamUseCase);
        return respond(StudentExamDto.fromModel(studentExam));
    }

}
