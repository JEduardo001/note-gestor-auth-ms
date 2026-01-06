package com.SwSOFTWARE.authMs.kafka.consumer;

import com.SwSOFTWARE.authMs.dto.user.DtoCreateUser;
import com.SwSOFTWARE.authMs.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final AuthService authService;
    private final ObjectMapper objectMapper;


    public Consumer(AuthService authService,ObjectMapper objectMapper){
        this.authService = authService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "userCreatedFailed", groupId = "users")
    public void userCreatedFailed(String request) throws JsonProcessingException {
        DtoCreateUser dtoCreateUser = objectMapper.readValue(request,DtoCreateUser.class);
        authService.deleteAuth(dtoCreateUser.id());
    }
}
