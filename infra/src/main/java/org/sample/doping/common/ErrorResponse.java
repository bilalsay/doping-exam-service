package org.sample.doping.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String errorDescription;
    private String errorType;
}
