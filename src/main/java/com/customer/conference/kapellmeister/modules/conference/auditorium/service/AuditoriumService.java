package com.customer.conference.kapellmeister.modules.conference.auditorium.service;

import com.customer.conference.kapellmeister.api.model.AuditoriumView;
import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.entity.AuditoriumEntity;

public interface AuditoriumService {

  AuditoriumEntity create(AuditoriumView auditoriumView);

  void remove(Long auditoriumId);
}
