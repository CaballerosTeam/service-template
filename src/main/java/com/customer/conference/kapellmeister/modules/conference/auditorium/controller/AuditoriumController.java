package com.customer.conference.kapellmeister.modules.conference.auditorium.controller;

import com.customer.conference.kapellmeister.api.controller.AuditoriumApi;
import com.customer.conference.kapellmeister.api.model.AuditoriumView;
import com.customer.conference.kapellmeister.modules.conference.auditorium.service.AuditoriumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuditoriumController implements AuditoriumApi {

  private final AuditoriumService auditoriumService;

  @Override
  public ResponseEntity<Void> createAuditorium(AuditoriumView auditoriumView) {
    auditoriumService.create(auditoriumView);

    return ResponseEntity.noContent().build();
  }
}
