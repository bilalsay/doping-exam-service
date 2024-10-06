package org.sample.doping.adapters;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.student.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamFakeDataAdapter implements ExamPort {

    @Override
    public Optional<Exam> retrieveExam(Long examId) {
        return Optional.of(Exam.builder().id(examId).build());
    }

    @Override
    @Transactional
    public Exam saveExam(Exam exam) {
        exam.setId(33L);
        return exam;
    }

    @Override
    public void assignExamToStudent(Exam exam, Student student) {

    }

    @Override
    public List<StudentExam> retrieveStudentExams(Long studentId) {
        return List.of(StudentExam.builder()
                .id(studentId)
                .answers(Map.of(1L, "A", 2L, "C"))
                .build()
        );
    }

    @Override
    public Optional<StudentExam> retrieveStudentExam(Long studentId, Long examId) {
        return Optional.of(StudentExam.builder()
                .id(studentId)
                .exam(Exam.builder().id(examId).build())
                .student(Student.builder().id(studentId).build())
                .answers(Map.of(1L, "A", 2L, "C"))
                .build());
    }

    @Override
    public void completeStudentExam(StudentExam studentExam) {

    }
}
