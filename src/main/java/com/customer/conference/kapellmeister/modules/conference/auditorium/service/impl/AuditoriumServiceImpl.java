package com.customer.conference.kapellmeister.modules.conference.auditorium.service.impl;

import com.customer.conference.kapellmeister.api.model.AuditoriumView;
import com.customer.conference.kapellmeister.modules.conference.auditorium.mapper.AuditoriumMapper;
import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.AuditoriumRepository;
import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.entity.AuditoriumEntity;
import com.customer.conference.kapellmeister.modules.conference.auditorium.service.AuditoriumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditoriumServiceImpl implements AuditoriumService {

  private final AuditoriumRepository auditoriumRepository;
  private final AuditoriumMapper auditoriumMapper;

  @Override
  public AuditoriumEntity create(AuditoriumView auditoriumView) {
    AuditoriumEntity auditoriumEntity = auditoriumMapper.map(auditoriumView);

    return auditoriumRepository.save(auditoriumEntity);
  }

  @Override
  public void remove(Long auditoriumId) {
  }
}
