package com.example.QRcode.exception.custom;

public class CustomNotFoundException extends BaseCustomException{

    public CustomNotFoundException(CustomError customError) {
        super(customError);
    }
    
}
