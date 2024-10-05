package org.sample.doping.common.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message, (Throwable)null, true, true);
    }
}
