package com.asherflo.employeeservice.service;

import com.asherflo.employeeservice.dto.APIResponseDto;
import com.asherflo.employeeservice.dto.EmployeeDto;
import org.springframework.stereotype.Service;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployee(Long EmployeeId);
}
