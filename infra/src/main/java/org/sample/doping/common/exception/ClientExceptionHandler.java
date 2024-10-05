package org.sample.doping.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sample.doping.common.ErrorResponse;
import org.sample.doping.common.Response;
import org.sample.doping.common.ResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ClientExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                e.getMessage(), e.getClass().getSimpleName());
        return ResponseBuilder.build(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public Response<ErrorResponse> handleNotFoundException(NotFoundException e) {
        var errorResponse = new ErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), e.getMessage(),
                e.getClass().getSimpleName());
        return ResponseBuilder.build(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public Response<ErrorResponse> handleBadRequestException(BadRequestException e) {
        var errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()), e.getMessage(),
                e.getClass().getSimpleName());
        return ResponseBuilder.build(errorResponse);
    }
}