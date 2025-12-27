package com.SwSOFTWARE.authMs.exception.exceptionHanlder;

import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithoutData;
import com.SwSOFTWARE.authMs.exception.auth.PasswordsDoNotMatchException;
import com.SwSOFTWARE.authMs.exception.auth.AuthEmailAlreadyInUseException;
import com.SwSOFTWARE.authMs.exception.auth.AuthNotFoundException;
import com.SwSOFTWARE.authMs.exception.auth.AuthUsernameAlreadyInUseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public ResponseEntity PasswordsDoNotMatchException(PasswordsDoNotMatchException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Passwords do not match "
        ));
    }


    @ExceptionHandler(AuthEmailAlreadyInUseException.class)
    public ResponseEntity UserEmailAlreadyInUseException(AuthEmailAlreadyInUseException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Email already in use "
        ));
    }

    @ExceptionHandler(AuthUsernameAlreadyInUseException.class)
    public ResponseEntity UserUsernameAlreadyInUse(AuthUsernameAlreadyInUseException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Username already in use "
        ));
    }

    @ExceptionHandler(AuthNotFoundException.class)
    public ResponseEntity UserNotFoundException(AuthNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoResponseApiWithoutData(
                HttpStatus.NOT_FOUND.value(),
                "User not found"
        ));
    }


}
