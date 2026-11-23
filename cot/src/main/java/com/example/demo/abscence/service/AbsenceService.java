package com.example.demo.abscence.service;


import com.example.demo.abscence.dto.AbsenceDto;
import com.example.demo.abscence.entity.Absence;
import com.example.demo.abscence.mapper.AbsenceMapper;
import com.example.demo.abscence.repository.AbsenceRepository;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private AbsenceMapper absenceMapper;

    @Autowired
    private UserRepository userRepository;



    public List<AbsenceDto> getAllAbsences() {
        return absenceRepository.findAll().stream()
                .map(absenceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AbsenceDto> getAbsencesByEmployeeId(Long employeeId) {
        return absenceRepository.findByEmployeeId(employeeId).stream()
                .map(absenceMapper::toDto)
                .collect(Collectors.toList());
    }

    public AbsenceDto addAbsence(AbsenceDto dto) {
        Absence absence = absenceMapper.toEntity(dto);
        Absence saved = absenceRepository.save(absence);
        return absenceMapper.toDto(saved);
    }

    public AbsenceDto updateJustification(Long id, Boolean justified) {
        Absence absence = absenceRepository.findById(id).orElse(null);
        if (absence == null)
            return null;

        absence.setJustified(justified);
        Absence saved = absenceRepository.save(absence);
        return absenceMapper.toDto(saved);
    }

    public void deleteAbsence(Long id) {
        absenceRepository.deleteById(id);
    }
}