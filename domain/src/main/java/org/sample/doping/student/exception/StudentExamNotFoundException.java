package org.sample.doping.student.exception;

import org.sample.doping.common.exception.NotFoundException;

public class StudentExamNotFoundException extends NotFoundException {
    public StudentExamNotFoundException(String message) {
        super(message);
    }
}
