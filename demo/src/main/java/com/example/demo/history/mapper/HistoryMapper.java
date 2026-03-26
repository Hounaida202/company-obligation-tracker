package com.example.demo.history.mapper;

import com.example.demo.history.dto.HistoryDto;
import com.example.demo.history.entity.History;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {
    public HistoryDto toDto(History entity) {
        if (entity == null) return null;
        return HistoryDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .referenceId(entity.getReferenceId())
                .title(entity.getTitle())
                .amount(entity.getAmount())
                .currency(entity.getCurrency())
                .paymentDate(entity.getPaymentDate())
                .build();
    }

    public History toEntity(HistoryDto dto) {
        if (dto == null) return null;
        return History.builder()
                .id(dto.getId())
                .type(dto.getType())
                .referenceId(dto.getReferenceId())
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .paymentDate(dto.getPaymentDate())
                .build();
    }
}
