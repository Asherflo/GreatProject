package com.asherflo.departmentservice.service;

import com.asherflo.departmentservice.dto.DepartmentDto;
import com.asherflo.departmentservice.model.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
   Department getDepartmentByCode(String departmentCode);
   DepartmentDto getDepartmentUsingCode(String departmentCode);
   List<DepartmentDto> getAllDepartments();
   void deleteDepartment(Long departmentId);

}
