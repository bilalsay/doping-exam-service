package org.sample.doping.exam.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sample.doping.common.AbstractEntity;
import org.sample.doping.exam.model.Exam;
import org.sample.doping.exam.model.Question;


@Getter
@Setter
@Entity
@Table(name = "exam", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamEntity extends AbstractEntity {

    private String name;

    public Exam toModel() {
        return Exam.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
