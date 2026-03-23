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

    public ObligationDto updateStatus(Long id, String status) {

        Obligation obligation = obligationRepository.findById(id).orElse(null);
        if (obligation == null)
            return null;

        obligation.setStatus(status);
        Obligation saved = obligationRepository.save(obligation);

        if ("paid".equalsIgnoreCase(status)) {

            if (obligation.getDueDate() != null && obligation.getDuration() != null) {

                String durationStr = obligation.getDuration().trim().toLowerCase();
                LocalDate nextDueDate = null;

                try {
                    int months = Integer.parseInt(durationStr);
                    nextDueDate = obligation.getDueDate().plusMonths(months);
                } catch (NumberFormatException e) {

                    if (durationStr.contains("quotidien") || durationStr.contains("journalier") || durationStr.contains("daily")) {
                        nextDueDate = obligation.getDueDate().plusDays(1);
                    } else if (durationStr.contains("hebdomadaire") || durationStr.contains("weekly")) {
                        nextDueDate = obligation.getDueDate().plusWeeks(1);
                    } else if (durationStr.contains("mensuel") || durationStr.contains("monthly")) {
                        nextDueDate = obligation.getDueDate().plusMonths(1);
                    } else if (durationStr.contains("annuel") || durationStr.contains("yearly") || durationStr.contains("annual")) {
                        nextDueDate = obligation.getDueDate().plusYears(1);
                    }
                }

                if (nextDueDate != null) {
                    obligation.setDueDate(nextDueDate);
                    obligation.setStatus("pending");
                    saved = obligationRepository.save(obligation);
                }
            }
        }

        return obligationMapper.toDto(saved);
    }

}