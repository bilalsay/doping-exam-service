package org.sample.doping.exam.port;

import java.util.List;
import java.util.Optional;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.student.model.Student;

public interface ExamPort {

    Optional<Exam> retrieveExam(Long examId);

    Exam saveExam(Exam exam);

    void assignExamToStudent(Exam exam, Student student);

    List<StudentExam> retrieveStudentExams(Long studentId);

    Optional<StudentExam> retrieveStudentExam(Long studentId, Long examId);

    void completeStudentExam(StudentExam studentExam);
}
