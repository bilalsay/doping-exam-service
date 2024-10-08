package org.sample.doping.exam.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.sample.doping.common.AbstractEntity;
import org.sample.doping.exam.model.StudentExam;
import org.sample.doping.student.jpa.entity.StudentEntity;


@Getter
@Setter
@Entity
@Table(name = "student_exam", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudentExamEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private ExamEntity exam;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "answers", columnDefinition = "json")
    @Builder.Default
    private Map<Long, String> answers = new HashMap<>();

    public StudentExam toModel() {
        return StudentExam.builder()
                .id(getId())
                .student(getStudent().toModel())
                .exam(getExam().toModel())
                .answers(getAnswers())
                .build();
    }
}
