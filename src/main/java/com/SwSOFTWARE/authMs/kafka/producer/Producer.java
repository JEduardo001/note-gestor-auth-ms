package com.SwSOFTWARE.authMs.kafka.producer;

import com.SwSOFTWARE.authMs.dto.user.DtoCreateUser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(DtoCreateUser request) {
        System.out.println("envie el mensaje");
        kafkaTemplate.send("auth.created", request);
    }

    public void publisFailedSendEventDlq(String request) {
        kafkaTemplate.send("failedSendEventDlq", request);
    }
}
