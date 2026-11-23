package com.example.demo.abscence.controller;


import com.example.demo.abscence.dto.AbsenceDto;
import com.example.demo.abscence.service.AbsenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/absences")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AbsenceDto> getAllAbsences() {
        return absenceService.getAllAbsences();
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('EMPLOYEE') and @employeeService.isOwner(#employeeId, principal.username))")
    public List<AbsenceDto> getAbsencesByEmployeeId(@PathVariable Long employeeId) {
        return absenceService.getAbsencesByEmployeeId(employeeId);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AbsenceDto addAbsence(@Valid @RequestBody AbsenceDto dto) {
        return absenceService.addAbsence(dto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public AbsenceDto updateStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        return absenceService.updateJustification(id, body.get("justified"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")

    public void deleteAbsence(@PathVariable Long id) {
        absenceService.deleteAbsence(id);
    }
}