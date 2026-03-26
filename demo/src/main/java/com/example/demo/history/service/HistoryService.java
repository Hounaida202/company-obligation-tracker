package com.example.demo.history.service;

import com.example.demo.history.dto.HistoryDto;
import com.example.demo.history.entity.History;
import com.example.demo.history.mapper.HistoryMapper;
import com.example.demo.history.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryMapper historyMapper;

    public List<HistoryDto> getAllHistory() {
        return historyRepository.findAll().stream()
                .map(historyMapper::toDto)
                .collect(Collectors.toList());
    }

    public HistoryDto saveHistory(HistoryDto historyDto) {
        History entity = historyMapper.toEntity(historyDto);
        History saved = historyRepository.save(entity);
        return historyMapper.toDto(saved);
    }
}