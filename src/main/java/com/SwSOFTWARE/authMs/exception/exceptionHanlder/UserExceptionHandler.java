package com.SwSOFTWARE.authMs.exception.exceptionHanlder;

import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithoutData;
import com.SwSOFTWARE.authMs.exception.user.PasswordsDoNotMatchException;
import com.SwSOFTWARE.authMs.exception.user.UserEmailAlreadyInUseException;
import com.SwSOFTWARE.authMs.exception.user.UserNotFoundException;
import com.SwSOFTWARE.authMs.exception.user.UserUsernameAlreadyInUseException;
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


    @ExceptionHandler(UserEmailAlreadyInUseException.class)
    public ResponseEntity UserEmailAlreadyInUseException(UserEmailAlreadyInUseException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Email already in use "
        ));
    }

    @ExceptionHandler(UserUsernameAlreadyInUseException.class)
    public ResponseEntity UserUsernameAlreadyInUse(UserUsernameAlreadyInUseException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Username already in use "
        ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity UserNotFoundException(UserNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoResponseApiWithoutData(
                HttpStatus.NOT_FOUND.value(),
                "User not found"
        ));
    }


}
