package org.sample.doping.exam.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.sample.doping.exam.jpa.entity.ExamEntity;
import org.sample.doping.exam.jpa.entity.QuestionEntity;
import org.sample.doping.exam.jpa.entity.StudentExamEntity;
import org.sample.doping.exam.jpa.repository.ExamRepository;
import org.sample.doping.exam.jpa.repository.QuestionRepository;
import org.sample.doping.exam.jpa.repository.StudentExamRepository;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.exam.port.ExamPort;
import org.sample.doping.student.jpa.entity.StudentEntity;
import org.sample.doping.student.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamDataAdapter implements ExamPort {

    private final ExamRepository examRepository;

    private final QuestionRepository questionRepository;

    private final StudentExamRepository studentExamRepository;

    @Override
    public Optional<Exam> retrieveExam(Long examId) {
        return examRepository.findById(examId)
                .map(ExamEntity::toModel);
    }

    @Override
    @Transactional
    public Exam saveExam(Exam exam) {
        var examEntity = ExamEntity.builder()
                .name(exam.getName())
                .build();

        examRepository.save(examEntity);

        var questions = exam.getQuestions().stream()
                .map(question -> getQuestionEntity(question, examEntity))
                .toList();

        questionRepository.saveAll(questions);
        return examEntity.toModel();
    }

    @Override
    public void assignExamToStudent(Exam exam, Student student) {
        var studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        var examEntity = new ExamEntity();
        examEntity.setId(exam.getId());
        var studentExam = StudentExamEntity.builder()
                .student(studentEntity)
                .exam(examEntity)
                .build();
        studentExamRepository.save(studentExam);
    }

    @Override
    public List<StudentExam> retrieveStudentExams(Long studentId) {
        return studentExamRepository.findAllByStudentId(studentId).stream()
                .map(StudentExamEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentExam> retrieveStudentExam(Long studentId, Long examId) {
        return studentExamRepository.findByStudentIdAndExamId(studentId, examId)
                .map(StudentExamEntity::toModel);
    }

    @Override
    public void completeStudentExam(StudentExam studentExam) {
        var examEntity = new ExamEntity();
        examEntity.setId(studentExam.getExam().getId());

        var studentEntity = new StudentEntity();
        studentEntity.setId(studentExam.getStudent().getId());

        var studentExamEntity = new StudentExamEntity();
        studentExamEntity.setId(studentExam.getId());
        studentExamEntity.setStudent(studentEntity);
        studentExamEntity.setExam(examEntity);
        studentExamEntity.setAnswers(studentExam.getAnswers());
        studentExamRepository.save(studentExamEntity);
    }

    private QuestionEntity getQuestionEntity(Question question, ExamEntity examEntity) {
        return QuestionEntity.builder()
                .number(question.getNumber())
                .text(question.getText())
                .exam(examEntity)
                .selections(question.getSelections())
                .correctSelection(question.getCorrectSelection())
                .build();
    }
}
