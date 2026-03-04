package com.bhawesh.createCode.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.List;


public record ApiError(
        HttpStatus httpStatus,
        String message,
        Instant timeStamp,
        @JsonInclude(JsonInclude.Include.NON_NULL) List<ApiErrorField> errors

) {
    public ApiError(HttpStatus httpStatus,String message){
        this(httpStatus,message,Instant.now(),null);
    }

    public ApiError(HttpStatus httpStatus,String message,List<ApiErrorField> errors){
        this(httpStatus,message,Instant.now(),errors);
    }

}

record ApiErrorField(
        String field,
        String error
){}
