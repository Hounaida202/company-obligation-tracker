package com.example.demo.employee.mapper;


import com.example.demo.employee.dto.EmployeeDto;
import com.example.demo.employee.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeDto dto);
}
