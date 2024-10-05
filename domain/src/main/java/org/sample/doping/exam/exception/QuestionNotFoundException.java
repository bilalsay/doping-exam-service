package org.sample.doping.exam.exception;

import org.sample.doping.common.exception.NotFoundException;

public class QuestionNotFoundException extends NotFoundException {
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
