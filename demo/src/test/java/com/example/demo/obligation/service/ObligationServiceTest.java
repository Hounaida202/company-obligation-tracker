package com.example.demo.obligation.service;

import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.entity.Obligation;
import com.example.demo.obligation.mapper.ObligationMapper;
import com.example.demo.obligation.repository.ObligationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObligationServiceTest {

    @Mock
    private ObligationRepository obligationRepository;

    @Mock
    private ObligationMapper obligationMapper;

    @InjectMocks
    private ObligationService obligationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllObligations() {
        Obligation ob1 = new Obligation();
        Obligation ob2 = new Obligation();
        ObligationDto dto1 = new ObligationDto();
        ObligationDto dto2 = new ObligationDto();

        when(obligationRepository.findAll()).thenReturn(Arrays.asList(ob1, ob2));
        when(obligationMapper.toDto(ob1)).thenReturn(dto1);
        when(obligationMapper.toDto(ob2)).thenReturn(dto2);

        List<ObligationDto> result = obligationService.getAllObligations();

        assertEquals(2, result.size());
        verify(obligationRepository, times(1)).findAll();
    }

    @Test
    void testAddObligation() {
        ObligationDto dto = new ObligationDto();
        dto.setDuration("1"); // 1 mois
        Obligation entity = new Obligation();
        Obligation savedEntity = new Obligation();
        ObligationDto savedDto = new ObligationDto();

        when(obligationMapper.toEntity(dto)).thenReturn(entity);
        when(obligationRepository.save(entity)).thenReturn(savedEntity);
        when(obligationMapper.toDto(savedEntity)).thenReturn(savedDto);

        ObligationDto result = obligationService.addObligation(dto);

        assertNotNull(result);
        verify(obligationRepository, times(1)).save(entity);
    }

    @Test
    void testUpdateStatusPaid() {
        Obligation ob = new Obligation();
        ob.setDueDate(LocalDate.now());
        ob.setDuration("1");
        ObligationDto dto = new ObligationDto();
        Obligation savedOb = new Obligation();
        ObligationDto savedDto = new ObligationDto();

        when(obligationRepository.findById(1L)).thenReturn(Optional.of(ob));
        when(obligationRepository.save(any(Obligation.class))).thenReturn(savedOb);
        when(obligationMapper.toDto(savedOb)).thenReturn(savedDto);

        ObligationDto result = obligationService.updateStatus(1L, "paid");

        assertNotNull(result);
        verify(obligationRepository, atLeastOnce()).save(any(Obligation.class));
    }
}