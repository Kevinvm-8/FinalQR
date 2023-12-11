package com.example.QRcode.exception.custom;

public class CustomBadRequestException extends BaseCustomException{

    public CustomBadRequestException(CustomError customError) {
        super(customError);
    }
    
}
