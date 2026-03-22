package com.example.demo.obligation.service;

import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.entity.Obligation;
import com.example.demo.obligation.mapper.ObligationMapper;
import com.example.demo.obligation.repository.ObligationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public ObligationDto addObligation(ObligationDto dto) {
        dto.setFirstStarted(LocalDate.now().toString());

        if (dto.getDuration() != null && !dto.getDuration().trim().isEmpty()) {
            try {
                int months = Integer.parseInt(dto.getDuration().trim());
                dto.setDueDate(LocalDate.now().plusMonths(months).toString());
            } catch (NumberFormatException e) {
            }
        }

        Obligation obligation = obligationMapper.toEntity(dto);
        Obligation saved = obligationRepository.save(obligation);
        return obligationMapper.toDto(saved);
    }
    public ObligationDto updateObligation(Long id, ObligationDto dto) {
        if (!obligationRepository.existsById(id))
            return null;

        Obligation obligation = obligationMapper.toEntity(dto);
        obligation.setId(id);

        Obligation saved = obligationRepository.save(obligation);
        return obligationMapper.toDto(saved);
    }

    public void deleteObligation(Long id) {
        obligationRepository.deleteById(id);
    }

}