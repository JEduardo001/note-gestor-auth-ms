package com.SwSOFTWARE.authMs.repository;

import com.SwSOFTWARE.authMs.entity.OutboxEventEntity;
import com.SwSOFTWARE.authMs.enums.StatusEventEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, UUID> {
    List<OutboxEventEntity> findByStatus(StatusEventEnum typeEvent);
}
