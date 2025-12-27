package com.SwSOFTWARE.authMs.controller;

import com.SwSOFTWARE.authMs.service.JwtService;
import org.aspectj.weaver.ast.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final JwtService jwtService;

    public TestController(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> generateToken(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(jwtService.createToken(username));
    }

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.status(HttpStatus.OK).body("hola mundo desde auth ms");
    }

    @GetMapping("/t")
    public ResponseEntity<String> testWithSecurity(){
        return ResponseEntity.status(HttpStatus.OK).body("hola desde  auth con seguridad");
    }


}
