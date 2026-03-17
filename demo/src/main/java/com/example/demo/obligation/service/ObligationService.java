package com.example.demo.obligation.service;

import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.mapper.ObligationMapper;
import com.example.demo.obligation.repository.ObligationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObligationService {

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private ObligationMapper obligationMapper;


    public List<ObligationDto> getAllObligations() {
        return obligationRepository.findAll().stream()
                .map(obligationMapper::toDto)
                .collect(Collectors.toList());
    }

}