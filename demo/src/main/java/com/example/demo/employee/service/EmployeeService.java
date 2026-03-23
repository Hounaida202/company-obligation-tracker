package com.example.demo.employee.service;

import com.example.demo.employee.dto.EmployeeDto;
import com.example.demo.employee.mapper.EmployeeMapper;
import com.example.demo.employee.repository.EmployeeRepository;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto)
                .orElse(null);
    }

    public boolean isOwner(Long employeeId, String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (user.getEmployeeId() == null) return false;
                    try {
                        return Long.parseLong(user.getEmployeeId()) == employeeId;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .orElse(false);
    }

}