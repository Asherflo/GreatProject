package com.asherflo.departmentservice.controller;

import com.asherflo.departmentservice.dto.DepartmentDto;
import com.asherflo.departmentservice.model.Department;
import com.asherflo.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private DepartmentService departmentService;
 @PostMapping("/")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
     DepartmentDto savedDepartment=  departmentService.saveDepartment(departmentDto);
     return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

//    @GetMapping("/{departmentCode}")
//    public Department getDepartmentByCode(@PathVariable String departmentCode){
//    return departmentService.getDepartmentByCode(departmentCode);
//    }

    @GetMapping("/{departmentCode}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("departmentCode") String departmentCode){
     DepartmentDto departmentDto = departmentService.getDepartmentUsingCode(departmentCode);
    return  new ResponseEntity<>(departmentDto,HttpStatus.OK);
 }


    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        List<DepartmentDto> departmentDto = departmentService.getAllDepartments();
        return  new ResponseEntity<>(departmentDto,HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("departmentId") Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
