package com.customer.conference.kapellmeister.modules.conference.auditorium.mapper;

import com.customer.conference.kapellmeister.api.model.AuditoriumView;
import com.customer.conference.kapellmeister.modules.conference.auditorium.repository.entity.AuditoriumEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface AuditoriumMapper {

  AuditoriumEntity map(AuditoriumView view);

  AuditoriumView map(AuditoriumEntity entity);
}
