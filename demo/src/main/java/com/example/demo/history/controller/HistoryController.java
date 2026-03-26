package com.example.demo.history.controller;

import com.example.demo.history.dto.HistoryDto;
import com.example.demo.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryDto>> getAllHistory() {
        return ResponseEntity.ok(historyService.getAllHistory());
    }
}