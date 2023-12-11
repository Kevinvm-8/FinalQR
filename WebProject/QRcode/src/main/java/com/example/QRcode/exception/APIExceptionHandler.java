package com.example.QRcode.exception;//package com.qradmin.exception;
//
//import com.accountservice.exception.custom.CustomBadRequestException;
//import com.accountservice.exception.custom.CustomNotFoundException;
//import com.accountservice.model.account.CustomError;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.Map;
//
//@RestControllerAdvice
//public class APIExceptionHandler {
//
//    @ExceptionHandler(CustomBadRequestException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//
//    public Map<String, com.accountservice.model.account.CustomError> badRequestException(CustomBadRequestException ex) {
//        return ex.getErrors();
//    }
//
//    @ExceptionHandler(CustomNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//
//    public Map<String, CustomError> notFoundException(CustomNotFoundException ex) {
//        return ex.getErrors();
//    }
//}
