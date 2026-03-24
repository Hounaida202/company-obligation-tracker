package com.example.demo.employee.service;

import com.example.demo.employee.dto.EmployeeDto;
import com.example.demo.employee.entity.Employee;
import com.example.demo.employee.mapper.EmployeeMapper;
import com.example.demo.employee.repository.EmployeeRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = employeeMapper.toEntity(dto);
        Employee saved = employeeRepository.save(employee);

        String baseUsername = saved.getFirstName().toLowerCase().replaceAll("\\s+", "") + "."
                + saved.getLastName().toLowerCase().replaceAll("\\s+", "");
        String username = baseUsername;
        int suffix = 1;
        while(userRepository.findByUsername(username).isPresent()) {
            username = baseUsername + suffix;
            suffix++;
        }

        String password = UUID.randomUUID().toString().substring(0, 8);

        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role("EMPLOYEE")
                .name(saved.getFirstName() + " " + saved.getLastName())
                .employeeId(String.valueOf(saved.getId()))
                .build();
        userRepository.save(user);

        EmployeeDto result = employeeMapper.toDto(saved);
        result.setGeneratedUsername(username);
        result.setGeneratedPassword(password);
        return result;
    }



}