package org.sample.doping.student.exception;

import org.sample.doping.common.exception.BadRequestException;

public class StudentAllReadyExistsException extends BadRequestException {
    public StudentAllReadyExistsException(String message) {
        super(message);
    }
}
