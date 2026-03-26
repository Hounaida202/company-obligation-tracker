package com.example.demo.employee.controller;


import com.example.demo.employee.dto.EmployeeDto;
import com.example.demo.employee.dto.EmployeeSalaryDto;
import com.example.demo.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeDto addEmployee(@Valid @RequestBody EmployeeDto dto) {
        return employeeService.addEmployee(dto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDto dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/{id}/salary")
    public EmployeeSalaryDto getEmployeeSalary(@PathVariable Long id) {
        return employeeService.getEmployeeSalary(id);
    }

    @PostMapping("/category/{categoryId}/pay")
    public void payCategory(@PathVariable Long categoryId) {
        employeeService.payCategoryThisMonth(categoryId);
    }
}
