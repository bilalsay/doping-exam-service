package org.sample.doping.student.exception;

import org.sample.doping.common.exception.NotFoundException;

public class StudentNotFoundException extends NotFoundException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
