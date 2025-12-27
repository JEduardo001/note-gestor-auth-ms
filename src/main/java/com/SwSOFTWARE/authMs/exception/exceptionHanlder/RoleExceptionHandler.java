package com.SwSOFTWARE.authMs.exception.exceptionHanlder;

import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithoutData;
import com.SwSOFTWARE.authMs.exception.role.RoleNameAlreadyInUse;
import com.SwSOFTWARE.authMs.exception.role.RoleNotFoundException;
import com.SwSOFTWARE.authMs.exception.user.PasswordsDoNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoleExceptionHandler {

    @ExceptionHandler(RoleNameAlreadyInUse.class)
    public ResponseEntity RoleNameAlreadyInUse(RoleNameAlreadyInUse ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DtoResponseApiWithoutData(
                HttpStatus.BAD_REQUEST.value(),
                "Role name already in use "
        ));
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity RoleNotFoundException(RoleNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DtoResponseApiWithoutData(
                HttpStatus.NOT_FOUND.value(),
                "Role not found "
        ));
    }
}
