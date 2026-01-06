package com.SwSOFTWARE.authMs.controller;

import com.SwSOFTWARE.authMs.conostants.ApiBase;
import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithoutData;
import com.SwSOFTWARE.authMs.dto.auth.DtoCreateAuth;
import com.SwSOFTWARE.authMs.dto.api.DtoResponseApiWithData;
import com.SwSOFTWARE.authMs.dto.auth.DtoLogin;
import com.SwSOFTWARE.authMs.dto.auth.DtoUpdateAuth;
import com.SwSOFTWARE.authMs.service.AuthService;
import com.SwSOFTWARE.authMs.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping()
    public ResponseEntity<DtoResponseApiWithData> getAllAuths(@RequestParam Integer page, @RequestParam Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData<>(
                HttpStatus.OK.value(),
                "Auths obtained",
                authService.getAllAuth(page,size)
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<DtoResponseApiWithData> createAuth(@Valid @RequestBody DtoCreateAuth request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(new DtoResponseApiWithData(
                HttpStatus.CREATED.value(),
                "Auth Created",
                authService.createUser(request)
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<DtoResponseApiWithData> login(@Valid @RequestBody DtoLogin request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(),request.password())
        );

        String token = jwtService.createToken(request.username());

        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData(
                HttpStatus.OK.value(),
                "logged",
                token
        ));
    }


    @GetMapping("/{idAuth}")
    public ResponseEntity<DtoResponseApiWithData> getAuth(@PathVariable UUID idAuth){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData<>(
                HttpStatus.OK.value(),
                "Auth obtained",
                authService.getAuth(idAuth)
        ));
    }

    @PutMapping()
    public ResponseEntity<DtoResponseApiWithData> update(@Valid @RequestBody DtoUpdateAuth request){
        return ResponseEntity.status(HttpStatus.OK).body(new DtoResponseApiWithData(
                HttpStatus.OK.value(),
                "Auth updated",
                authService.updateAuth(request)
        ));
    }

}
