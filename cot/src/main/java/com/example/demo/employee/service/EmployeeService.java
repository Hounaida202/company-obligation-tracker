package com.example.demo.employee.service;

import com.example.demo.abscence.entity.Absence;
import com.example.demo.abscence.repository.AbsenceRepository;
import com.example.demo.employee.dto.EmployeeDto;
import com.example.demo.employee.dto.EmployeeSalaryDto;
import com.example.demo.employee.entity.Employee;
import com.example.demo.employee.mapper.EmployeeMapper;
import com.example.demo.employee.repository.EmployeeRepository;
import com.example.demo.history.entity.History;
import com.example.demo.history.repository.HistoryRepository;
import com.example.demo.jobCategory.entity.JobCategory;
import com.example.demo.jobCategory.repository.JobCategoryRepository;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;


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

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        if (!employeeRepository.existsById(id))
            return null;
        Employee employee = employeeMapper.toEntity(dto);
        employee.setId(id);
        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDto(saved);
    }

    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
    }

    public EmployeeSalaryDto getEmployeeSalary(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null)
            return null;

        List<Absence> absences = absenceRepository.findByEmployeeId(id);

        double baseHourlyRate = employee.getBaseSalary() / 160.0;
        double basePerMinute = baseHourlyRate / 60.0;

        double deduction = 0;
        int monthCount = 0;
        int currentMonth = java.time.LocalDate.now().getMonthValue();

        for (Absence abs : absences) {
            if (abs.getDate() != null && abs.getDate().getMonthValue() == currentMonth) {
                monthCount++;
            }
            if (abs.getJustified() != null && !abs.getJustified()) {
                deduction += abs.getDurationMinutes() * basePerMinute;
            }
        }

        return EmployeeSalaryDto.builder()
                .baseSalary(employee.getBaseSalary())
                .totalDeduction(deduction)
                .netSalary(employee.getBaseSalary() - deduction)
                .absencesCountThisMonth(monthCount)
                .build();
    }

    public void payCategoryThisMonth(Long categoryId) {
        JobCategory category = jobCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        double totalCatSalary = employeeRepository.findByJobId(categoryId).stream()
                .map(emp -> Optional.ofNullable(getEmployeeSalary(emp.getId()))
                        .map(EmployeeSalaryDto::getNetSalary)
                        .orElse(0.0))
                .mapToDouble(Double::doubleValue)
                .sum();

        if (totalCatSalary > 0) {
            History history = History.builder()
                    .type("EMPLOYEE_CATEGORY")
                    .referenceId(categoryId)
                    .title("Salaires - " + category.getName())
                    .amount(totalCatSalary)
                    .currency("MAD")
                    .paymentDate(java.time.LocalDate.now())
                    .build();
            historyRepository.save(history);
        }
    }



}