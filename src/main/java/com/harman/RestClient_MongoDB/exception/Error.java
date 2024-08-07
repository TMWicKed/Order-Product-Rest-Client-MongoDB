package com.harman.RestClient_MongoDB.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class Error {

    private String errorCode;

    private Object errorMessage;

    private HttpStatus httpStatus;

}

