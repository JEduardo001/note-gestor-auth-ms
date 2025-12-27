package com.SwSOFTWARE.authMs.controller;

import com.SwSOFTWARE.authMs.conostants.ApiBase;
import com.SwSOFTWARE.authMs.dto.auth.DtoCreateUser;
import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithData;
import com.SwSOFTWARE.authMs.dto.auth.DtoLogin;
import com.SwSOFTWARE.authMs.service.AuthService;
import com.SwSOFTWARE.authMs.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiBase.apiBase + "auth")
public class AuthController {


    private final JwtService jwtService;
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtService jwtService,AuthService authService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<DtoResponseApiWithData> createUser(@Valid @RequestBody DtoCreateUser request){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "User Created",
                authService.createUser(request)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponseApiWithData> login(@Valid @RequestBody DtoLogin request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );

        String token = jwtService.createToken(request.username());

        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "logged",
                token
        ));
    }


    @GetMapping("/t")
    public ResponseEntity<DtoResponseApiWithData> test(){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "Hola desde auth con autenticacion requerida",
                null
        ));
    }

    @GetMapping()
    public ResponseEntity<DtoResponseApiWithData> testLibre(){
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "Hola desde auth sin auth",
                null
        ));
    }

}
