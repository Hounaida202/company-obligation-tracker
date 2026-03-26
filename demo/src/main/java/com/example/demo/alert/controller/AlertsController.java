package com.example.demo.alert.controller;

import com.example.demo.alert.dto.AlertsDto;
import com.example.demo.alert.service.AlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alerts")
public class AlertsController {

    @Autowired
    private AlertsService alertsService;

    @GetMapping
    public AlertsDto getAlerts() {
        return alertsService.getAlerts();
    }
}