package org.sample.doping.common;

public class Response<T> {

    private T data;
    private ErrorResponse error;

    public Response() {
    }

    public Response(ErrorResponse error) {
        this.error = error;
    }

    public Response(T data) {
        this.data = data;
    }

    public ErrorResponse getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}
