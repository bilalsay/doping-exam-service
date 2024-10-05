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
import org.sample.doping.exam.model.Question;


@Getter
@Setter
@Entity
@Table(name = "question", schema = "public")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QuestionEntity extends AbstractEntity {

    private Integer number;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private ExamEntity exam;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "selections", columnDefinition = "json")
    @Builder.Default
    private Map<String, String> selections = new HashMap<>();

    private String correctSelection;

    public Question toModel() {
        return Question.builder()
                .id(getId())
                .number(getNumber())
                .text(getText())
                .selections(getSelections())
                .correctSelection(getCorrectSelection())
                .build();
    }
}
