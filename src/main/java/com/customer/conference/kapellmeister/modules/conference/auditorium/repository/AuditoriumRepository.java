package com.customer.conference.kapellmeister.modules.conference.auditorium.repository;

import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.entity.AuditoriumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditoriumRepository extends JpaRepository<AuditoriumEntity, Long> {
}
