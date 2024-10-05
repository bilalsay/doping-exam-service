package org.sample.doping.common.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message, (Throwable)null, true, true);
    }
}
