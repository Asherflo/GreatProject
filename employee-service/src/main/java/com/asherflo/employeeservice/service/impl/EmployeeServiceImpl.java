package com.asherflo.employeeservice.service.impl;

import com.asherflo.employeeservice.dto.APIResponseDto;
import com.asherflo.employeeservice.dto.DepartmentDto;
import com.asherflo.employeeservice.dto.EmployeeDto;
import com.asherflo.employeeservice.model.Employee;
import com.asherflo.employeeservice.repository.EmployeeRepository;
import com.asherflo.employeeservice.service.APIClient;
import com.asherflo.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
//    private RestTemplate restTemplate;
//    private WebClient webClient;
    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(employeeDto.getId(), employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );

        Employee savedemployee = employeeRepository.save(employee);

        return new EmployeeDto(savedemployee.getId(),
                savedemployee.getFirstName(),
                savedemployee.getLastName(),
                savedemployee.getEmail(),
                savedemployee.getDepartmentCode()
        );
    }

    @Override
    public  APIResponseDto getEmployee(Long employeeId) {
//       Optional<Employee > employee = Optional.ofNullable(employeeRepository.findById(employeeId)
//               .orElseThrow(() -> new RuntimeException("id cannot be found")));
////       Employee actualEmployee = employee.get();
//       EmployeeDto savedEmployee =new EmployeeDto();
//       savedEmployee.setId(employee.get().getId());
//       savedEmployee.setFirstName(employee.get().getFirstName());
//       savedEmployee.setLastName(employee.get().getLastName());
//       savedEmployee.setEmail(employee.get().getEmail());
//        return savedEmployee;;
//    }
        Employee employee = employeeRepository.findById(employeeId).get();
//         ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/" +
//                         employee.getDepartmentCode(), DepartmentDto.class);
//         DepartmentDto departmentDto = responseEntity.getBody();

//      DepartmentDto departmentDto =  webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();


       DepartmentDto departmentDto= apiClient.getDepartment(employee.getDepartmentCode());

            EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartmentCode()
            );
        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

            return apiResponseDto;
    }

}