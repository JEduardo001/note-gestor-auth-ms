package com.SwSOFTWARE.authMs.service;

import com.SwSOFTWARE.authMs.dto.user.DtoCreateUser;
import com.SwSOFTWARE.authMs.entity.OutboxEventEntity;
import com.SwSOFTWARE.authMs.enums.StatusEventEnum;
import com.SwSOFTWARE.authMs.enums.TypeEventEnum;
import com.SwSOFTWARE.authMs.kafka.producer.Producer;
import com.SwSOFTWARE.authMs.repository.OutboxEventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OutboxEventService {

    private final OutboxEventRepository outboxEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Producer producer;

    public OutboxEventService(OutboxEventRepository outboxEventRepository,KafkaTemplate<String, Object> kafkaTemplate,
                              ObjectMapper objectMapper,Producer producer){
        this.outboxEventRepository = outboxEventRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.producer = producer;

    }

    public void saveEvent(DtoCreateUser request) throws Exception{
        outboxEventRepository.save(OutboxEventEntity.builder()
                        .aggregateId(request.id())
                        .eventType(TypeEventEnum.CREATE)
                        .payload(objectMapper.writeValueAsString(request))
                        .status(StatusEventEnum.PENDING)
                        .retryCount(1)
                        .created_at(LocalDateTime.now())
                .build());
    }


    @Scheduled(fixedDelay = 500)
    public void publishPendingEvents() {
        List<OutboxEventEntity> events = outboxEventRepository.findByStatus(StatusEventEnum.PENDING);

        for (OutboxEventEntity e : events) {
            try {
                producer.send(objectMapper.readValue(e.getPayload(), DtoCreateUser.class));
                e.setStatus(StatusEventEnum.SENT);
                outboxEventRepository.save(e);
            } catch (Exception ex) {

                e.setRetryCount(e.getRetryCount() + 1);
                if (e.getRetryCount() > 20) {
                    e.setStatus(StatusEventEnum.FAILED);
                    producer.publisFailedSendEventDlq(e.getPayload());
                }

                outboxEventRepository.save(e);
            }
        }
    }

}
