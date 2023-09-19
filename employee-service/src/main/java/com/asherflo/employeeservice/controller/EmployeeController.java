package com.asherflo.employeeservice.controller;

import com.asherflo.employeeservice.dto.APIResponseDto;
import com.asherflo.employeeservice.dto.EmployeeDto;
import com.asherflo.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> savedEmployee (@RequestBody EmployeeDto employeeDto){
        EmployeeDto employee =  employeeService.saveEmployee(employeeDto);
        return  new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable("employeeId")  Long employeeId){
        APIResponseDto apiResponseDto = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);

    }
}
