package com.example.demo.abscence.mapper;

import com.example.demo.abscence.dto.AbsenceDto;
import com.example.demo.abscence.entity.Absence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {
    AbsenceDto toDto(Absence absence);

    Absence toEntity(AbsenceDto dto);
}
