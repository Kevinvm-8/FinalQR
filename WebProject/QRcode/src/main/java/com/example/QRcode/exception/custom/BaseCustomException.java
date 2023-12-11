package com.example.QRcode.exception.custom;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class BaseCustomException extends Exception {
    private Map<String, CustomError> errors;

    public BaseCustomException(CustomError customError) {
        this.errors = new HashMap<>();
        this.errors.put("errors", customError);
    }
     
}
