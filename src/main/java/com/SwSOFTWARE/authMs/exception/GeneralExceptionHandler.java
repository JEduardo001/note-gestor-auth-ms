package com.SwSOFTWARE.authMs.exception;

import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithoutData;
import com.SwSOFTWARE.authMs.exception.user.UserEmailAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity MissingPathVariableException(MissingPathVariableException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Please send all the required data"
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "The given data does not meet the requirements"
        ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Please provide the correct data type"
        ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity Exception(Exception ex){
        ex.printStackTrace();
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DtoResponseApiWithoutData(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        ));
    }

}
