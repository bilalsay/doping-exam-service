package org.sample.doping.student.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sample.doping.common.AbstractEntity;
import org.sample.doping.student.model.Student;


@Getter
@Setter
@Entity
@Table(name = "student", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudentEntity extends AbstractEntity {

    private String name;
    private String surname;
    private String number;

    public Student toModel() {
        return Student.builder()
                .id(getId())
                .name(getName())
                .surname(getSurname())
                .number(getNumber())
                .build();
    }
}
