package com.example.QRcode.exception.custom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomError {
    private String message;
    private String code;
    private String traces;
}
