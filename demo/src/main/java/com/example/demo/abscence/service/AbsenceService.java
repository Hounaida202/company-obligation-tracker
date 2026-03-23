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


}