package com.example.demo.obligation.controller;

import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.service.ObligationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/obligations")
public class ObligationController {

    @Autowired
    private ObligationService obligationService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<ObligationDto> getAllObligations() {
        return obligationService.getAllObligations();
    }

    @PostMapping
    public ObligationDto addObligation(@Valid @RequestBody ObligationDto dto) {
        return obligationService.addObligation(dto);
    }

    @PutMapping("/{id}")
    public ObligationDto updateObligation(@PathVariable Long id, @Valid @RequestBody ObligationDto dto) {
        return obligationService.updateObligation(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteObligation(@PathVariable Long id) {
        obligationService.deleteObligation(id);
    }

    @PatchMapping("/{id}")
    public ObligationDto updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return obligationService.updateStatus(id, body.get("status"));
    }

}