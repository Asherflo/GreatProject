package com.asherflo.departmentservice.service.impl;

import com.asherflo.departmentservice.dto.DepartmentDto;
import com.asherflo.departmentservice.model.Department;
import com.asherflo.departmentservice.repository.DepartmentRepository;
import com.asherflo.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentDto.getDepartmentCode());
        if (existingDepartment != null) {
            throw new RuntimeException();
        }
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode());
        return savedDepartmentDto;
    }

    @Override
    public Department getDepartmentByCode(String departmentCode) {
        return  departmentRepository.findByDepartmentCode(departmentCode);
    }

    @Override
    public DepartmentDto getDepartmentUsingCode(String departmentCode) {
        // pass JPA entity into department entity
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        // convert department into departmentDto.
        DepartmentDto getDepartment = new DepartmentDto(department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode());
        return getDepartment;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for (Department department : departments) {
            DepartmentDto departmentDto = new DepartmentDto(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    department.getDepartmentCode()
            );
            departmentDtos.add(departmentDto);
        }
        return departmentDtos;
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        departmentRepository.delete(department.get());
    }

}
