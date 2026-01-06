package com.SwSOFTWARE.authMs.entity;

import com.SwSOFTWARE.authMs.enums.StatusEventEnum;
import com.SwSOFTWARE.authMs.enums.TypeEventEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "outboxEvent")
public class OutboxEventEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID aggregateId;
    @Enumerated(EnumType.STRING)
    private TypeEventEnum eventType;
    private String payload;
    @Enumerated(EnumType.STRING)
    private StatusEventEnum status;
    private Integer retryCount;
    private LocalDateTime created_at;
}
