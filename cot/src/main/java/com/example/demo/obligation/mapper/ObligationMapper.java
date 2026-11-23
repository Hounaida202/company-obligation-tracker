package com.example.demo.obligation.mapper;


import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.entity.Obligation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObligationMapper {
    ObligationDto toDto(Obligation obligation);

    Obligation toEntity(ObligationDto dto);
}
