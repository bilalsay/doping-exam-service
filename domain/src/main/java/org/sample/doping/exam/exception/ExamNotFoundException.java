package org.sample.doping.exam.exception;

import org.sample.doping.common.exception.NotFoundException;

public class ExamNotFoundException extends NotFoundException {
    public ExamNotFoundException(String message) {
        super(message);
    }
}
