package com.asherflo.departmentservice.repository;

import com.asherflo.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long>{
    Department findByDepartmentCode(String departmentCode);
}
